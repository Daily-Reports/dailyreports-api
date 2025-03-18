package org.dailyreports.controller;

import org.dailyreports.dto.note.NoteDto;
import org.dailyreports.dto.note.NoteUpdateDto;
import org.dailyreports.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<?> find(@RequestParam(required = false) Long id) {
        if (id != null)
            return ResponseEntity.ok(noteService.findById(id));

        return ResponseEntity.ok(noteService.findAll());
    }

    @PostMapping()
    public ResponseEntity<NoteDto> create(@Valid @RequestBody NoteDto data) {
        return new ResponseEntity<>(noteService.save(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<NoteDto> update(@PathVariable long id, @RequestBody NoteUpdateDto data) {
        return ResponseEntity.ok(noteService.update(id, data));
    }

    @PutMapping(path = "/{id}/add-image", consumes = "multipart/form-data")
    public ResponseEntity<NoteDto> addImage(@PathVariable long id,
                                         @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(noteService.addImage(id, image));
    }

    @DeleteMapping("/{id}/remove-image")
    public ResponseEntity<NoteDto> removeImage(@PathVariable long id, @RequestParam String imageUrl) {
        return ResponseEntity.ok(noteService.removeImage(id, imageUrl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        noteService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}