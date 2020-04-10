package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.DeliveryDTO;
import ua.lviv.iot.domain.DeliveryEntity;
import ua.lviv.iot.exceptions.NoSuchDeliveryException;
import ua.lviv.iot.service.DeliveryService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @GetMapping(value = "/api/delivery/{delivery_id}")
    public ResponseEntity<DeliveryDTO> getDelivery(@PathVariable Integer delivery_id) throws NoSuchDeliveryException {
        DeliveryEntity delivery = deliveryService.getDelivery(delivery_id);
        Link link = linkTo(methodOn(DeliveryController.class).getDelivery(delivery_id)).withSelfRel();

        DeliveryDTO deliveryDTO = new DeliveryDTO(delivery, link);

        return new ResponseEntity<>(deliveryDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/delivery")
    public ResponseEntity<List<DeliveryDTO>> getAllDelivery() throws NoSuchDeliveryException {
        List<DeliveryEntity> deliveryList = deliveryService.getAllDelivery();
        Link link = linkTo(methodOn(DeliveryController.class).getAllDelivery()).withSelfRel();

        List<DeliveryDTO> deliveryDTO = new ArrayList<>();
        for (DeliveryEntity entity : deliveryList ) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getWayOfDeliveryId()).withSelfRel();
            DeliveryDTO dto = new DeliveryDTO(entity, selfLink);
            deliveryDTO.add(dto);
        }

        return new ResponseEntity<>(deliveryDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/delivery")
    public ResponseEntity<DeliveryDTO> addDelivery(@RequestBody DeliveryEntity newDelivery) throws NoSuchDeliveryException {
        deliveryService.createDelivery(newDelivery);
        Link link = linkTo(methodOn(DeliveryController.class).getDelivery((int) newDelivery.getWayOfDeliveryId())).withSelfRel();

        DeliveryDTO delivery = new DeliveryDTO(newDelivery, link);

        return new ResponseEntity<>(delivery, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/delivery/{delivery_id}")
    public ResponseEntity<DeliveryDTO> updateDelivery(@RequestBody DeliveryEntity uDelivery, @PathVariable Integer delivery_id) throws NoSuchDeliveryException {
        deliveryService.updateDelivery(uDelivery, delivery_id);
        DeliveryEntity artist = deliveryService.getDelivery(delivery_id);
        Link link = linkTo(methodOn(DeliveryController.class).getDelivery(delivery_id)).withSelfRel();

        DeliveryDTO deliveryDTO = new DeliveryDTO(artist, link);

        return new ResponseEntity<>(deliveryDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/delivery/{delivery_id}")
    public  ResponseEntity deleteDelivery(@PathVariable Integer delivery_id) throws NoSuchDeliveryException {
        deliveryService.deleteDelivery(delivery_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
