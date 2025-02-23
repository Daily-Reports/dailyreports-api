package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.EventDto;
import com.vasconcellos.dailyreport.model.Event;
import com.vasconcellos.dailyreport.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    public Event save(EventDto data) {
        Event event = new Event();
        event.setName(data.getName());

        return eventRepository.save(event);
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

}
