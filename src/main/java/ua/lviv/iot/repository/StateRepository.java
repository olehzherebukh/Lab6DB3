package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.StateEntity;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Integer> {
}