package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.EventHasArtistEntity;

@Repository
public interface EventHasArtistRepository extends JpaRepository<EventHasArtistEntity, Integer> {
}
