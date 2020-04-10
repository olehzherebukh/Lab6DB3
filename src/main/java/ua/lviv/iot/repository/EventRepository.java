package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer> {
}