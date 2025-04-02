package org.dailyreports.service;

import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.event.EventDto;
import org.dailyreports.dto.event.EventUpdateDto;
import org.dailyreports.exception.ResourceNotFoundException;
import org.dailyreports.mapper.EventMapper;
import org.dailyreports.model.Event;
import org.dailyreports.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventDto save(EventDto data) {
        Event event = eventMapper.toEntity(data);

        return eventMapper.toDto(eventRepository.save(event));
    }

    public EventDto update(long id, EventUpdateDto data) {
        Event area = findByIdAsEntity(id);

        if(data.getName() != null)
            area.setName(data.getName());

        return eventMapper.toDto(eventRepository.save(area));
    }

    public EventDto findById(Long id) {
        return eventMapper.toDto(findByIdAsEntity(id));
    }

    public Event findByIdAsEntity(Long id) {
        return eventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Event cannot be found."));
    }

    public List<EventDto> findAll() {
        return eventRepository.findAll().stream().map(eventMapper::toDto).toList();
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}