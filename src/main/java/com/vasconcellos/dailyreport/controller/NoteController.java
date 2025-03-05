package com.vasconcellos.dailyreport.controller;

import com.vasconcellos.dailyreport.dto.NoteDto;
import com.vasconcellos.dailyreport.dto.NoteUpdateCommentDto;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.model.Note;
import com.vasconcellos.dailyreport.service.NoteService;
import com.vasconcellos.dailyreport.service.S3Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notes")
public class NoteController {

    private final NoteService noteService;
    private final S3Service s3Service;

    @GetMapping
    public ResponseEntity<?> find(@RequestParam(required = false) Long id) {
        if (id != null)
            return ResponseEntity.ok(noteService.findById(id));

        return ResponseEntity.ok(noteService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Note> create(@Valid @RequestBody NoteDto data) {
        return new ResponseEntity<>(noteService.save(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update-comment")
    public ResponseEntity<Note> updateComment(@PathVariable long id, @RequestBody NoteUpdateCommentDto data) {
        Note note = noteService.findById(id);
        note.setComment(data.getComment());

        return ResponseEntity.ok(noteService.save(note));
    }

    @PutMapping(path = "/{id}/add-image", consumes = "multipart/form-data")
    public ResponseEntity<String> addImage(@PathVariable long id,
                                         @RequestParam("image") MultipartFile image) throws IOException {
        Note note = noteService.findById(id);

        String url = s3Service.uploadFile(image);
        note.getImagesUrl().add(url);

        noteService.save(note);

        return ResponseEntity.ok(url);
    }

    @DeleteMapping("/{id}/remove-image")
    public ResponseEntity<Note> removeImage(@PathVariable long id, @RequestParam String imageUrl) {
        Note note = noteService.findById(id);

        if (!note.getImagesUrl().remove(imageUrl))
            throw new ResourceNotFoundException("Image cannot be found");

        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        s3Service.deleteFile(imageName);

        return ResponseEntity.ok(noteService.save(note));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        noteService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}