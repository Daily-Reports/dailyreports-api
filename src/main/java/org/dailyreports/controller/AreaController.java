package org.dailyreports.controller;

import org.dailyreports.dto.AreaDto;
import org.dailyreports.service.AreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/areas")
public class AreaController {

    private final AreaService areaService;

    @GetMapping()
    public ResponseEntity<List<AreaDto>> findAll() {
        return new ResponseEntity<>(areaService.findAll(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<AreaDto> create(@Valid @RequestBody AreaDto data) {
        return new ResponseEntity<>(areaService.save(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        areaService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}