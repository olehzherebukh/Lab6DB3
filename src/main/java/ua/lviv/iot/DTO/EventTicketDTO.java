package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.domain.EventTicketEntity;
import ua.lviv.iot.exceptions.NoSuchEventTicketException;

public class EventTicketDTO extends ResourceSupport {
    EventTicketEntity eventTicket;
    public EventTicketDTO(EventTicketEntity eventTicket, Link selfLink) throws NoSuchEventTicketException {
        this.eventTicket = eventTicket;
        add(selfLink);
    }

    public int getEventTicketId() {
        return eventTicket.getEventTicketId();
    }

    public String getDate() {
        return eventTicket.getDate();
    }

    public String getTime() {
        return eventTicket.getTime();
    }

    public String getPlace() {
        return eventTicket.getPlace();
    }

    public int getEventEventId() {
        return eventTicket.getEventEventId();
    }

    public Byte getIsAvailable() {
        return eventTicket.getIsAvailable();
    }

    public int getStateId() {
        return eventTicket.getStateId();
    }

    public String getSectorName() {
        return eventTicket.getSectorName();
    }

    public int getDeliveryWayOfDeliveryId() {
        return eventTicket.getDeliveryWayOfDeliveryId();
    }

}
