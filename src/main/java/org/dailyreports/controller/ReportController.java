package org.dailyreports.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.ReportDto;
import org.dailyreports.mapper.ReportMapper;
import org.dailyreports.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/reports")
public class ReportController {

    private final ReportService reportService;
    private final ReportMapper reportMapper;

    @GetMapping
    public ResponseEntity<?> find(@RequestParam(required = false) Long id) {
        if (id != null)
            return ResponseEntity.ok(reportService.findById(id));

        return ResponseEntity.ok(reportService.findAll());
    }

    @PostMapping()
    public ResponseEntity<ReportDto> create(@Valid @RequestBody ReportDto data) {
        return new ResponseEntity<>(reportService.save(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        reportService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}