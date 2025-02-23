package com.vasconcellos.dailyreport.controller;

import com.vasconcellos.dailyreport.dto.AreaDto;
import com.vasconcellos.dailyreport.model.Area;
import com.vasconcellos.dailyreport.service.AreaService;
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
    public ResponseEntity<List<Area>> findAll() {
        return new ResponseEntity<>(areaService.findAll(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Area> create(@Valid @RequestBody AreaDto data) {
        return new ResponseEntity<>(areaService.save(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        areaService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
