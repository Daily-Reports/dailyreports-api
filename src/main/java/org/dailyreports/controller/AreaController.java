package org.dailyreports.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.area.AreaDto;
import org.dailyreports.dto.area.AreaUpdateDto;
import org.dailyreports.service.AreaService;
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


    @PutMapping("/{id}/update")
    public ResponseEntity<AreaDto> update(@PathVariable long id, @RequestBody AreaUpdateDto data) {
        return ResponseEntity.ok(areaService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        areaService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}