package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.EventTicketDTO;
import ua.lviv.iot.domain.EventTicketEntity;
import ua.lviv.iot.exceptions.NoSuchEventException;
import ua.lviv.iot.exceptions.NoSuchEventTicketException;
import ua.lviv.iot.service.EventTicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class EventTicketController {
    @Autowired
    EventTicketService eventTicketService;

    @GetMapping(value = "/api/eventTicket/{eventTicket_id}")
    public ResponseEntity<EventTicketDTO> getEventTicket(@PathVariable Integer eventTicket_id) throws NoSuchEventTicketException {
        EventTicketEntity eventTicket = eventTicketService.getEventTicket(eventTicket_id);
        Link link = linkTo(methodOn(EventTicketController.class).getEventTicket(eventTicket_id)).withSelfRel();

        EventTicketDTO eventTicketDTO = new EventTicketDTO(eventTicket, link);

        return new ResponseEntity<>(eventTicketDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/eventTicket")
    public ResponseEntity<List<EventTicketDTO>> getAllEventTicket() throws NoSuchEventTicketException {
        List<EventTicketEntity> eventTicketList = eventTicketService.getAllEventTicket();
        Link link = linkTo(methodOn(EventTicketController.class).getAllEventTicket()).withSelfRel();

        List<EventTicketDTO> eventTicketDTO = new ArrayList<>();
        for (EventTicketEntity entity : eventTicketList ) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getEventTicketId()).withSelfRel();
            EventTicketDTO dto = new EventTicketDTO(entity, selfLink);
            eventTicketDTO.add(dto);
        }

        return new ResponseEntity<>(eventTicketDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/eventTicket")
    public ResponseEntity<EventTicketDTO> addEventTicket(@RequestBody EventTicketEntity newEventTicket) throws NoSuchEventTicketException {
        eventTicketService.createEventTicket(newEventTicket);
        Link link = linkTo(methodOn(EventTicketController.class).getEventTicket((int) newEventTicket.getEventTicketId())).withSelfRel();

        EventTicketDTO eventTicket = new EventTicketDTO(newEventTicket, link);

        return new ResponseEntity<>(eventTicket, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/eventTicket/{eventTicket_id}")
    public ResponseEntity<EventTicketDTO> updateEventTicket(@RequestBody EventTicketEntity uEventTicket, @PathVariable Integer eventTicket_id) throws NoSuchEventTicketException {
        eventTicketService.updateEventTicket(uEventTicket, eventTicket_id);
        EventTicketEntity eventTicket = eventTicketService.getEventTicket(eventTicket_id);
        Link link = linkTo(methodOn(EventTicketController.class).getEventTicket(eventTicket_id)).withSelfRel();

        EventTicketDTO eventTicketDTO = new EventTicketDTO(eventTicket, link);

        return new ResponseEntity<>(eventTicketDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/eventTicket/{eventTicket_id}")
    public  ResponseEntity deleteEventTicket(@PathVariable Integer eventTicket_id) throws NoSuchEventTicketException {
        eventTicketService.deleteEventTicket(eventTicket_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
