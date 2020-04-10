package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.StateDTO;
import ua.lviv.iot.domain.StateEntity;
import ua.lviv.iot.exceptions.NoSuchStateException;
import ua.lviv.iot.service.StateService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class StateController {
    @Autowired
    StateService stateService;

    @GetMapping(value = "/api/state/{state_id}")
    public ResponseEntity<StateDTO> getState(@PathVariable Integer state_id) throws NoSuchStateException {
        StateEntity state = stateService.getState(state_id);
        Link link = linkTo(methodOn(StateController.class).getState(state_id)).withSelfRel();

        StateDTO stateDTO = new StateDTO(state, link);

        return new ResponseEntity<>(stateDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/state")
    public ResponseEntity<List<StateDTO>> getAllState() throws NoSuchStateException {
        List<StateEntity> stateList = stateService.getAllState();
        Link link = linkTo(methodOn(StateController.class).getAllState()).withSelfRel();

        List<StateDTO> stateDTO = new ArrayList<>();
        for (StateEntity entity : stateList ) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getStateId()).withSelfRel();
            StateDTO dto = new StateDTO(entity, selfLink);
            stateDTO.add(dto);
        }

        return new ResponseEntity<>(stateDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/state")
    public ResponseEntity<StateDTO> addState(@RequestBody StateEntity newState) throws NoSuchStateException {
        stateService.createState(newState);
        Link link = linkTo(methodOn(StateController.class).getState((int) newState.getStateId())).withSelfRel();

        StateDTO state = new StateDTO(newState, link);

        return new ResponseEntity<>(state, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/state/{state_id}")
    public ResponseEntity<StateDTO> updateState(@RequestBody StateEntity uState, @PathVariable Integer state_id) throws NoSuchStateException {
        stateService.updateState(uState, state_id);
        StateEntity state = stateService.getState(state_id);
        Link link = linkTo(methodOn(StateController.class).getState(state_id)).withSelfRel();

        StateDTO stateDTO = new StateDTO(state, link);

        return new ResponseEntity<>(stateDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/state/{state_id}")
    public  ResponseEntity deleteState(@PathVariable Integer state_id) throws NoSuchStateException {
        stateService.deleteState(state_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
