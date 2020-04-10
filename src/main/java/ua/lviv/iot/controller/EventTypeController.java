package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.EventTypeDTO;
import ua.lviv.iot.domain.EventTypeEntity;
import ua.lviv.iot.exceptions.NoSuchEventTypeException;
import ua.lviv.iot.service.EventTypeService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class EventTypeController {
    @Autowired
    EventTypeService eventTypeService;

    @GetMapping(value = "/api/eventType/{eventType_id}")
    public ResponseEntity<EventTypeDTO> getEventType(@PathVariable Integer eventType_id) throws NoSuchEventTypeException {
        EventTypeEntity eventType = eventTypeService.getEventType(eventType_id);
        Link link = linkTo(methodOn(EventTypeController.class).getEventType(eventType_id)).withSelfRel();

        EventTypeDTO eventTypeDTO = new EventTypeDTO(eventType, link);

        return new ResponseEntity<>(eventTypeDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/eventType")
    public ResponseEntity<List<EventTypeDTO>> getAllEventType() throws NoSuchEventTypeException {
        List<EventTypeEntity> eventTypeList = eventTypeService.getAllEventType();
        Link link = linkTo(methodOn(EventTypeController.class).getAllEventType()).withSelfRel();

        List<EventTypeDTO> eventTypeDTO = new ArrayList<>();
        for (EventTypeEntity entity : eventTypeList ) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getEventTypeId()).withSelfRel();
            EventTypeDTO dto = new EventTypeDTO(entity, selfLink);
            eventTypeDTO.add(dto);
        }

        return new ResponseEntity<>(eventTypeDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/eventType")
    public ResponseEntity<EventTypeDTO> addEventType(@RequestBody EventTypeEntity newEventType) throws NoSuchEventTypeException {
        eventTypeService.createEventType(newEventType);
        Link link = linkTo(methodOn(EventTypeController.class).getEventType((int) newEventType.getEventTypeId())).withSelfRel();

        EventTypeDTO eventType = new EventTypeDTO(newEventType, link);

        return new ResponseEntity<>(eventType, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/eventType/{eventType_id}")
    public ResponseEntity<EventTypeDTO> updateEventType(@RequestBody EventTypeEntity uEventType, @PathVariable Integer eventType_id) throws NoSuchEventTypeException {
        eventTypeService.updateEventType(uEventType, eventType_id);
        EventTypeEntity eventType = eventTypeService.getEventType(eventType_id);
        Link link = linkTo(methodOn(EventTypeController.class).getEventType(eventType_id)).withSelfRel();

        EventTypeDTO eventTypeDTO = new EventTypeDTO(eventType, link);

        return new ResponseEntity<>(eventTypeDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/eventType/{eventType_id}")
    public  ResponseEntity deleteEventType(@PathVariable Integer eventType_id) throws NoSuchEventTypeException {
        eventTypeService.deleteEventType(eventType_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
