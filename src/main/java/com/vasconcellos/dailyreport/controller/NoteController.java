package com.vasconcellos.dailyreport.controller;

import com.vasconcellos.dailyreport.dto.NoteDto;
import com.vasconcellos.dailyreport.model.Note;
import com.vasconcellos.dailyreport.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<?> find(@RequestParam(required = false) Long id) {
        if (id != null)
            return noteService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        return ResponseEntity.ok(noteService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Note> create(@Valid @RequestBody NoteDto data) {
        return new ResponseEntity<>(noteService.save(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        noteService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}