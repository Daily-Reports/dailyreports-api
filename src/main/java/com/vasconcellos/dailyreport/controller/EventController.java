package com.vasconcellos.dailyreport.controller;

import com.vasconcellos.dailyreport.dto.EventDto;
import com.vasconcellos.dailyreport.model.Event;
import com.vasconcellos.dailyreport.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/events")
public class EventController {

    private final EventService eventService;

    @GetMapping()
    public ResponseEntity<List<Event>> findAll() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Event> create(@Valid @RequestBody EventDto data) {
        return new ResponseEntity<>(eventService.save(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        eventService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
