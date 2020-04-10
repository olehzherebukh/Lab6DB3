package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.domain.EventEntity;
import ua.lviv.iot.exceptions.NoSuchEventException;

public class EventDTO extends ResourceSupport {
    EventEntity event;
    public EventDTO(EventEntity event, Link selfLink) throws NoSuchEventException {
        this.event = event;
        add(selfLink);
    }

    public int getEventId() {
        return event.getEventId();
    }

    public String getName() {
        return event.getName();
    }

    public String getCity() {
        return event.getCity();
    }

    public String getLocation() {
        return event.getName();
    }

    public int getEventTypeEventTypeId() {
        return event.getEventTypeEventTypeId();
    }
}
