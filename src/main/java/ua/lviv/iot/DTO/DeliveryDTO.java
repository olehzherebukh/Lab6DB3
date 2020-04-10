package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.domain.DeliveryEntity;
import ua.lviv.iot.exceptions.NoSuchDeliveryException;

public class DeliveryDTO extends ResourceSupport {
    DeliveryEntity delivery;
    public DeliveryDTO(DeliveryEntity delivery, Link selfLink) throws NoSuchDeliveryException {
        this.delivery = delivery;
        add(selfLink);
    }

    public int getWayOfDeliveryId() {
        return delivery.getWayOfDeliveryId();
    }

    public String getWayOfDelivery() {
        return delivery.getWayOfDelivery();
    }
}
