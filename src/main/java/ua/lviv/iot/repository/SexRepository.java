package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.SexEntity;

@Repository
public interface SexRepository extends JpaRepository<SexEntity, Integer> {
}