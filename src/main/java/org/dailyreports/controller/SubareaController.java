package org.dailyreports.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.subarea.SubareaDto;
import org.dailyreports.dto.subarea.SubareaUpdateDto;
import org.dailyreports.service.SubareaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/subareas")
public class SubareaController {

    private final SubareaService subareaService;

    @GetMapping()
    public ResponseEntity<List<SubareaDto>> findAll() {
        return new ResponseEntity<>(subareaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubareaDto> findById(@PathVariable long id) {
        return new ResponseEntity<>(subareaService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SubareaDto> create(@Valid @RequestBody SubareaDto data) {
        return new ResponseEntity<>(subareaService.save(data), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubareaDto> update(@PathVariable long id, @RequestBody SubareaUpdateDto data) {
        return ResponseEntity.ok(subareaService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        subareaService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}