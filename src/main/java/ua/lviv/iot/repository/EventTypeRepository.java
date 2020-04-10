package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.EventTypeEntity;

@Repository
public interface EventTypeRepository extends JpaRepository<EventTypeEntity, Integer> {
}