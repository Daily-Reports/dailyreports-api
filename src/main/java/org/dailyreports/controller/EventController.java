package org.dailyreports.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.event.EventDto;
import org.dailyreports.dto.event.EventUpdateDto;
import org.dailyreports.service.EventService;
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
    public ResponseEntity<List<EventDto>> findAll() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> findById(@PathVariable long id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EventDto> create(@Valid @RequestBody EventDto data) {
        return new ResponseEntity<>(eventService.save(data), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventDto> update(@PathVariable long id, @RequestBody EventUpdateDto data) {
        return ResponseEntity.ok(eventService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        eventService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}