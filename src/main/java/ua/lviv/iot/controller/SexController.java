package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.SexDTO;
import ua.lviv.iot.domain.SexEntity;
import ua.lviv.iot.exceptions.NoSuchSexException;
import ua.lviv.iot.service.SexService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class SexController {
    @Autowired
    SexService sexService;

    @GetMapping(value = "/api/sex/{sex_id}")
    public ResponseEntity<SexDTO> getSex(@PathVariable Integer sex_id) throws NoSuchSexException {
        SexEntity sex = sexService.getSex(sex_id);
        Link link = linkTo(methodOn(SexController.class).getSex(sex_id)).withSelfRel();

        SexDTO sexDTO = new SexDTO(sex, link);

        return new ResponseEntity<>(sexDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/sex")
    public ResponseEntity<List<SexDTO>> getAllSex() throws NoSuchSexException {
        List<SexEntity> sexList = sexService.getAllSex();
        Link link = linkTo(methodOn(SexController.class).getAllSex()).withSelfRel();

        List<SexDTO> sexDTO = new ArrayList<>();
        for (SexEntity entity : sexList ) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getSexId()).withSelfRel();
            SexDTO dto = new SexDTO(entity, selfLink);
            sexDTO.add(dto);
        }

        return new ResponseEntity<>(sexDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/sex")
    public ResponseEntity<SexDTO> addSex(@RequestBody SexEntity newSex) throws NoSuchSexException {
        sexService.createSex(newSex);
        Link link = linkTo(methodOn(SexController.class).getSex((int) newSex.getSexId())).withSelfRel();

        SexDTO sex = new SexDTO(newSex, link);

        return new ResponseEntity<>(sex, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/sex/{sex_id}")
    public ResponseEntity<SexDTO> updateSex(@RequestBody SexEntity uSex, @PathVariable Integer sex_id) throws NoSuchSexException {
        sexService.updateSex(uSex, sex_id);
        SexEntity sex = sexService.getSex(sex_id);
        Link link = linkTo(methodOn(SexController.class).getSex(sex_id)).withSelfRel();

        SexDTO sexDTO = new SexDTO(sex, link);

        return new ResponseEntity<>(sexDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/sex/{sex_id}")
    public  ResponseEntity deleteSex(@PathVariable Integer sex_id) throws NoSuchSexException {
        sexService.deleteSex(sex_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
