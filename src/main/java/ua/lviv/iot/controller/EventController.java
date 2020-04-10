package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.EventDTO;
import ua.lviv.iot.domain.EventEntity;
import ua.lviv.iot.exceptions.NoSuchEventException;
import ua.lviv.iot.exceptions.NoSuchEventTicketException;
import ua.lviv.iot.service.EventService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping(value = "/api/event/{event_id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Integer event_id) throws NoSuchEventException {
        EventEntity event = eventService.getEvent(event_id);
        Link link = linkTo(methodOn(EventController.class).getEvent(event_id)).withSelfRel();

        EventDTO eventDTO = new EventDTO(event, link);

        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/event")
    public ResponseEntity<List<EventDTO>> getAllEvent() throws NoSuchEventException {
        List<EventEntity> eventList = eventService.getAllEvent();
        Link link = linkTo(methodOn(EventController.class).getAllEvent()).withSelfRel();

        List<EventDTO> eventDTO = new ArrayList<>();
        for (EventEntity entity : eventList ) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getEventId()).withSelfRel();
            EventDTO dto = new EventDTO(entity, selfLink);
            eventDTO.add(dto);
        }

        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/event")
    public ResponseEntity<EventDTO> addEvent(@RequestBody EventEntity newEvent) throws NoSuchEventException {
        eventService.createEvent(newEvent);
        Link link = linkTo(methodOn(EventController.class).getEvent((int) newEvent.getEventId())).withSelfRel();

        EventDTO event = new EventDTO(newEvent, link);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/event/{event_id}")
    public ResponseEntity<EventDTO> updateEvent(@RequestBody EventEntity uEvent, @PathVariable Integer event_id) throws NoSuchEventException {
        eventService.updateEvent(uEvent, event_id);
        EventEntity event = eventService.getEvent(event_id);
        Link link = linkTo(methodOn(EventController.class).getEvent(event_id)).withSelfRel();

        EventDTO eventDTO = new EventDTO(event, link);

        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/event/{event_id}")
    public  ResponseEntity deleteEvent(@PathVariable Integer event_id) throws NoSuchEventException {
        eventService.deleteEvent(event_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
