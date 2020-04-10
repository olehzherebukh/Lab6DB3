package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.ArtistEntity;
import ua.lviv.iot.domain.EventHasArtistEntity;
import ua.lviv.iot.exceptions.NoSuchArtistException;
import ua.lviv.iot.exceptions.NoSuchEventHasArtistException;
import ua.lviv.iot.repository.ArtistRepository;
import ua.lviv.iot.repository.EventHasArtistRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventHasArtistService {
    @Autowired
    EventHasArtistRepository eventHasArtistRepository;

    public EventHasArtistEntity getEventHasArtist(Integer eventHasArtist_id) throws NoSuchEventHasArtistException {
        EventHasArtistEntity eventHasArtist = eventHasArtistRepository.findById(eventHasArtist_id).get();
        if (eventHasArtist == null) throw new NoSuchEventHasArtistException();
        return eventHasArtist;
    }

    public List<EventHasArtistEntity> getAllEventHasArtist() {
        return eventHasArtistRepository.findAll();
    }

    @Transactional
    public void createEventHasArtist(EventHasArtistEntity eventHasArtist) {
        eventHasArtistRepository.save(eventHasArtist);
    }

    @Transactional
    public void updateEventHasArtist(EventHasArtistEntity uEventHasArtist, Integer eventHasArtist_id) throws NoSuchEventHasArtistException {
        EventHasArtistEntity eventHasArtist = eventHasArtistRepository.findById(eventHasArtist_id).get();
        if (eventHasArtist == null) throw new NoSuchEventHasArtistException();
        eventHasArtist.setEventEventId(uEventHasArtist.getEventEventId());
        eventHasArtist.setArtistArtistId(uEventHasArtist.getArtistArtistId());
    }

    @Transactional
    public void deleteEventHasArtist(Integer eventHasArtist_id) throws NoSuchEventHasArtistException {
        Optional eventHasArtist = eventHasArtistRepository.findById(eventHasArtist_id);

        if (!eventHasArtist.isPresent()) throw new NoSuchEventHasArtistException();
        eventHasArtistRepository.delete((EventHasArtistEntity) eventHasArtist.get());
    }
}
