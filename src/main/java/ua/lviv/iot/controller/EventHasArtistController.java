package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.EventHasArtistDTO;
import ua.lviv.iot.domain.EventHasArtistEntity;
import ua.lviv.iot.exceptions.NoSuchEventHasArtistException;
import ua.lviv.iot.service.EventHasArtistService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class EventHasArtistController {
    @Autowired
    EventHasArtistService eventHasArtistService;

    @GetMapping(value = "/api/eventHasArtist/{eventHasArtist_id}")
    public ResponseEntity<EventHasArtistDTO> getEventHasArtist(@PathVariable Integer eventHasArtist_id) throws NoSuchEventHasArtistException {
        EventHasArtistEntity eventHasArtist = eventHasArtistService.getEventHasArtist(eventHasArtist_id);
        Link link = linkTo(methodOn(EventHasArtistController.class).getEventHasArtist(eventHasArtist_id)).withSelfRel();

        EventHasArtistDTO eventHasArtistDTO = new EventHasArtistDTO(eventHasArtist, link);

        return new ResponseEntity<>(eventHasArtistDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/eventHasArtist")
    public ResponseEntity<List<EventHasArtistDTO>> getAllEventHasArtist() throws NoSuchEventHasArtistException {
        List<EventHasArtistEntity> eventHasArtistList = eventHasArtistService.getAllEventHasArtist();
        Link link = linkTo(methodOn(EventHasArtistController.class).getAllEventHasArtist()).withSelfRel();

        List<EventHasArtistDTO> eventHasArtistDTO = new ArrayList<>();
        for (EventHasArtistEntity entity : eventHasArtistList ) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getEventEventId()).withSelfRel();
            EventHasArtistDTO dto = new EventHasArtistDTO(entity, selfLink);
            eventHasArtistDTO.add(dto);
        }

        return new ResponseEntity<>(eventHasArtistDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/eventHasArtist")
    public ResponseEntity<EventHasArtistDTO> addEventHasArtist(@RequestBody EventHasArtistEntity newEventHasArtist) throws NoSuchEventHasArtistException {
        eventHasArtistService.createEventHasArtist(newEventHasArtist);
        Link link = linkTo(methodOn(EventHasArtistController.class).getEventHasArtist((int) newEventHasArtist.getEventEventId())).withSelfRel();

        EventHasArtistDTO eventHasArtist = new EventHasArtistDTO(newEventHasArtist, link);

        return new ResponseEntity<>(eventHasArtist, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/eventHasArtist/{eventHasArtist_id}")
    public ResponseEntity<EventHasArtistDTO> updateEventHasArtist(@RequestBody EventHasArtistEntity uEventHasArtist, @PathVariable Integer eventHasArtist_id) throws NoSuchEventHasArtistException {
        eventHasArtistService.updateEventHasArtist(uEventHasArtist, eventHasArtist_id);
        EventHasArtistEntity eventHasArtist = eventHasArtistService.getEventHasArtist(eventHasArtist_id);
        Link link = linkTo(methodOn(EventHasArtistController.class).getEventHasArtist(eventHasArtist_id)).withSelfRel();

        EventHasArtistDTO eventHasArtistDTO = new EventHasArtistDTO(eventHasArtist, link);

        return new ResponseEntity<>(eventHasArtistDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/eventHasArtist/{eventHasArtist_id}")
    public  ResponseEntity deleteEventHasArtist(@PathVariable Integer eventHasArtist_id) throws NoSuchEventHasArtistException {
        eventHasArtistService.deleteEventHasArtist(eventHasArtist_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
