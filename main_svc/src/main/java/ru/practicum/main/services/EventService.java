package ru.practicum.main.services;

import ru.practicum.main.dto.*;
import ru.practicum.main.enums.EventState;
import ru.practicum.main.enums.SortValue;
import ru.practicum.main.models.Event;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventService {
    EventFullDto createEvent(Long userId, NewEventDto newEventDto);

    List<EventShortDto> getEvents(Long userId, Integer from, Integer size);

    EventFullDto updateEvent(Long eventId, UpdateEventAdminDto updateEventAdminDto);

    EventFullDto updateEventByUser(Long userId, Long eventId, UpdateEventUserDto updateEventUserDto);

    EventFullDto getEventByUser(Long userId, Long eventId);

    List<EventFullDto> getEventsWithParamsByAdmin(List<Long> users, EventState states, List<Long> categoriesId, String rangeStart, String rangeEnd, Integer from, Integer size);

    List<EventFullDto> getEventsWithParamsByUser(String text, List<Long> categories, Boolean paid, String rangeStart,
                                                 String rangeEnd, Boolean onlyAvailable, SortValue sort, Integer from, Integer size, HttpServletRequest request);

    EventFullDto getEvent(Long id, HttpServletRequest request);

    void setView(List<Event> events);
}
