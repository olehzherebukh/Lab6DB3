package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.ArtistEntity;
import ua.lviv.iot.domain.EventEntity;
import ua.lviv.iot.domain.EventTicketEntity;
import ua.lviv.iot.exceptions.NoSuchArtistException;
import ua.lviv.iot.exceptions.NoSuchEventException;
import ua.lviv.iot.exceptions.NoSuchEventTicketException;
import ua.lviv.iot.repository.ArtistRepository;
import ua.lviv.iot.repository.EventRepository;
import ua.lviv.iot.repository.EventTicketRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public EventEntity getEvent(Integer event_id) throws NoSuchEventException {
        EventEntity event = eventRepository.findById(event_id).get();
        if (event == null) throw new NoSuchEventException();
        return event;
    }

    public List<EventEntity> getAllEvent() {
        return eventRepository.findAll();
    }

    @Transactional
    public void createEvent(EventEntity event) {
        eventRepository.save(event);
    }

    @Transactional
    public void updateEvent(EventEntity uEvent, Integer event_id) throws NoSuchEventException {
        EventEntity event = eventRepository.findById(event_id).get();
        if (event == null) throw new NoSuchEventException();
        event.setEventId(uEvent.getEventId());
        event.setName(uEvent.getName());
        event.setCity(uEvent.getCity());
        event.setLocation(uEvent.getLocation());
        event.setEventTypeEventTypeId(uEvent.getEventTypeEventTypeId());
    }

    @Transactional
    public void deleteEvent(Integer event_id) throws NoSuchEventException {
        Optional event = eventRepository.findById(event_id);

        if (!event.isPresent()) throw new NoSuchEventException();
        eventRepository.delete((EventEntity) event.get());
    }
}
