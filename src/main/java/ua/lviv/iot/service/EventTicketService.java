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
public class EventTicketService {
    @Autowired
    EventTicketRepository eventTicketRepository;

    public EventTicketEntity getEventTicket(Integer eventTicket_id) throws NoSuchEventTicketException {
        EventTicketEntity eventTicket = eventTicketRepository.findById(eventTicket_id).get();
        if (eventTicket == null) throw new NoSuchEventTicketException();
        return eventTicket;
    }

    public List<EventTicketEntity> getAllEventTicket() {
        return eventTicketRepository.findAll();
    }

    @Transactional
    public void createEventTicket(EventTicketEntity eventTicket) {
        eventTicketRepository.save(eventTicket);
    }

    @Transactional
    public void updateEventTicket(EventTicketEntity uEventTicket, Integer eventTicket_id) throws NoSuchEventTicketException {
        EventTicketEntity eventTicket = eventTicketRepository.findById(eventTicket_id).get();
        if (eventTicket == null) throw new NoSuchEventTicketException();
        eventTicket.setEventTicketId(uEventTicket.getEventTicketId());
        eventTicket.setDate(uEventTicket.getDate());
        eventTicket.setTime(uEventTicket.getTime());
        eventTicket.setPlace(uEventTicket.getPlace());
        eventTicket.setEventEventId(uEventTicket.getEventEventId());
        eventTicket.setIsAvailable(uEventTicket.getIsAvailable());
        eventTicket.setStateId(uEventTicket.getStateId());
        eventTicket.setSectorName(uEventTicket.getSectorName());
        eventTicket.setDeliveryWayOfDeliveryId(uEventTicket.getDeliveryWayOfDeliveryId());

    }

    @Transactional
    public void deleteEventTicket(Integer eventTicket_id) throws NoSuchEventTicketException {
        Optional eventTicket = eventTicketRepository.findById(eventTicket_id);

        if (!eventTicket.isPresent()) throw new NoSuchEventTicketException();
        eventTicketRepository.delete((EventTicketEntity) eventTicket.get());
    }
}
