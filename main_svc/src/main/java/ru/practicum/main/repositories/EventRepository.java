package ru.practicum.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.main.models.Event;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "SELECT * from EVENTS " +
            "where initiator_id = :userId " +
            "limit :size offset :from", nativeQuery = true)
    List<Event> findAllWhereInitiatorIdWithLimitAndOffset(@Param("userId") Long userId, @Param("from") Integer from, @Param("size") Integer size);

    Optional<Event> findByIdAndInitiatorId(Long eventId, Long userId);

    List<Event> findAllByIdIn(List<Long> eventIds);

    Boolean existsByCategoryId(Long catId);

    Optional<Event> findByIdAndPublishedOnIsNotNull(Long id);
}
