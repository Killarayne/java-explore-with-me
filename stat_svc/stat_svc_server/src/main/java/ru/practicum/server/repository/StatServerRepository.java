package ru.practicum.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.server.model.EndpointHit;
import ru.practicum.server.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatServerRepository extends JpaRepository<EndpointHit, Long> {

    @Query(value = "select new ru.practicum.server.model.ViewStats(h.app, h.uri, count(h.ip)) " +
            "from  EndpointHit h " +
            "where h.timestamp between ?1 and ?2 " +
            "and h.uri in (?3) " +
            "group by h.app, h.uri " +
            "order by count(distinct h.ip) desc")
    List<ViewStats> getStatsByUrisAndIp(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(value = "select new ru.practicum.server.model.ViewStats(h.app, h.uri, count(h.ip)) " +
            "from  EndpointHit h " +
            "where h.timestamp between ?1 and ?2 " +
            "and h.uri in (?3) " +
            "group by h.app, h.uri " +
            "order by count(h.ip) desc")
    List<ViewStats> getStatsByUris(LocalDateTime start, LocalDateTime end, List<String> uris);

}
