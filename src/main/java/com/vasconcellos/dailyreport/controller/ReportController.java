package com.vasconcellos.dailyreport.controller;

import com.vasconcellos.dailyreport.dto.ReportDto;
import com.vasconcellos.dailyreport.model.Report;
import com.vasconcellos.dailyreport.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<?> find(@RequestParam(required = false) Long id) {
        if (id != null)
            return reportService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        return ResponseEntity.ok(reportService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Report> create(@Valid @RequestBody ReportDto data) {
        return new ResponseEntity<>(reportService.save(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        reportService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}