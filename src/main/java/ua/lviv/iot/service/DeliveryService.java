package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.ArtistEntity;
import ua.lviv.iot.domain.DeliveryEntity;
import ua.lviv.iot.exceptions.NoSuchArtistException;
import ua.lviv.iot.exceptions.NoSuchDeliveryException;
import ua.lviv.iot.repository.ArtistRepository;
import ua.lviv.iot.repository.DeliveryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public DeliveryEntity getDelivery(Integer delivery_id) throws NoSuchDeliveryException {
        DeliveryEntity delivery = deliveryRepository.findById(delivery_id).get();
        if (delivery == null) throw new NoSuchDeliveryException();
        return delivery;
    }

    public List<DeliveryEntity> getAllDelivery() {
        return deliveryRepository.findAll();
    }

    @Transactional
    public void createDelivery(DeliveryEntity delivery) {
        deliveryRepository.save(delivery);
    }

    @Transactional
    public void updateDelivery(DeliveryEntity uDelivery, Integer delivery_id) throws NoSuchDeliveryException {
        DeliveryEntity artist = deliveryRepository.findById(delivery_id).get();
        if (artist == null) throw new NoSuchDeliveryException();
        artist.setWayOfDeliveryId(uDelivery.getWayOfDeliveryId());
        artist.setWayOfDelivery(uDelivery.getWayOfDelivery());
    }

    @Transactional
    public void deleteDelivery(Integer delivery_id) throws NoSuchDeliveryException {
        Optional delivery = deliveryRepository.findById(delivery_id);

        if (!delivery.isPresent()) throw new NoSuchDeliveryException();
        deliveryRepository.delete((DeliveryEntity) delivery.get());
    }
}
