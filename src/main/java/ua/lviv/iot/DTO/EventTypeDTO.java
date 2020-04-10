package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.domain.EventTypeEntity;
import ua.lviv.iot.exceptions.NoSuchEventTypeException;

public class EventTypeDTO extends ResourceSupport {
    EventTypeEntity eventType;
    public EventTypeDTO(EventTypeEntity eventType, Link selfLink) throws NoSuchEventTypeException {
        this.eventType = eventType;
        add(selfLink);
    }

    public int getEventTypeId() {
        return eventType.getEventTypeId();
    }

    public String getTypeOfEvent() {
        return eventType.getTypeOfEvent();
    }
}
