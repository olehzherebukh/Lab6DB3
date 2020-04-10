package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.domain.EventHasArtistEntity;
import ua.lviv.iot.exceptions.NoSuchEventHasArtistException;

public class EventHasArtistDTO extends ResourceSupport {
    EventHasArtistEntity event;
    public EventHasArtistDTO(EventHasArtistEntity event, Link selfLink) throws NoSuchEventHasArtistException {
        this.event = event;
        add(selfLink);
    }

    public int getEventEventId() {
        return event.getEventEventId();
    }

    public int getArtistArtistId() {
        return event.getArtistArtistId();
    }
}
