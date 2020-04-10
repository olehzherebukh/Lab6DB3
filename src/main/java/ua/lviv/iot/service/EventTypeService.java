package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.ArtistEntity;
import ua.lviv.iot.domain.EventTypeEntity;
import ua.lviv.iot.exceptions.NoSuchArtistException;
import ua.lviv.iot.exceptions.NoSuchEventTypeException;
import ua.lviv.iot.repository.ArtistRepository;
import ua.lviv.iot.repository.EventTypeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventTypeService {
    @Autowired
    EventTypeRepository eventTypeRepository;

    public EventTypeEntity getEventType(Integer eventType_id) throws NoSuchEventTypeException {
        EventTypeEntity eventType = eventTypeRepository.findById(eventType_id).get();
        if (eventType == null) throw new NoSuchEventTypeException();
        return eventType;
    }

    public List<EventTypeEntity> getAllEventType() {
        return eventTypeRepository.findAll();
    }

    @Transactional
    public void createEventType(EventTypeEntity eventType) {
        eventTypeRepository.save(eventType);
    }

    @Transactional
    public void updateEventType(EventTypeEntity uEventType, Integer eventType_id) throws NoSuchEventTypeException {
        EventTypeEntity eventType = eventTypeRepository.findById(eventType_id).get();
        if (eventType == null) throw new NoSuchEventTypeException();
        eventType.setEventTypeId(uEventType.getEventTypeId());
        eventType.setTypeOfEvent(uEventType.getTypeOfEvent());
    }

    @Transactional
    public void deleteEventType(Integer eventType_id) throws NoSuchEventTypeException {
        Optional eventType = eventTypeRepository.findById(eventType_id);

        if (!eventType.isPresent()) throw new NoSuchEventTypeException();
        eventTypeRepository.delete((EventTypeEntity) eventType.get());
    }
}
