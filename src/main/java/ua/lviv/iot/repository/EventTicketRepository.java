package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.EventTicketEntity;

@Repository
public interface EventTicketRepository extends JpaRepository<EventTicketEntity, Integer> {
}