package ru.practicum.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.server.mapper.EndpointHitMapper;
import ru.practicum.server.mapper.ViewStatsMapper;
import ru.practicum.server.repository.StatServerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatServiceImpl implements StatService {

    private final StatServerRepository statServerRepository;
    private final EndpointHitMapper endpointHitMapper;
    private final ViewStatsMapper viewStatsMapper;

    @Override
    public void saveHit(EndpointHitDto endpointHitDto) {
        log.debug("Save hit by app: " + endpointHitDto.getApp());
        statServerRepository.save(endpointHitMapper.toEntity(endpointHitDto));
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        log.debug("Received stats.");
        return unique ? viewStatsMapper.toEntityList(statServerRepository.getStatsByUrisAndIp(start, end, uris))
                : viewStatsMapper.toEntityList(statServerRepository.getStatsByUris(start, end, uris));
    }

}
