package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.EventDto;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.mapper.EventMapper;
import com.vasconcellos.dailyreport.model.Event;
import com.vasconcellos.dailyreport.repository.EventRepository;
import lombok.RequiredArgsConstructor;
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