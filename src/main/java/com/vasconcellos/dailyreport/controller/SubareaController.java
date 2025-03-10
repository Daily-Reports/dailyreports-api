package com.vasconcellos.dailyreport.controller;

import com.vasconcellos.dailyreport.dto.SubareaDto;
import com.vasconcellos.dailyreport.service.SubareaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @PostMapping()
    public ResponseEntity<SubareaDto> create(@Valid @RequestBody SubareaDto data) {
        return new ResponseEntity<>(subareaService.save(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        subareaService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}