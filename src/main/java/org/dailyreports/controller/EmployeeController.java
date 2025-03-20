package org.dailyreports.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.EmployeeDto;
import org.dailyreports.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> find(@RequestParam(required = false) String name) {
        if (name != null)
            return ResponseEntity.ok(employeeService.findByName(name));

        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping()
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto data) {
        return new ResponseEntity<>(employeeService.save(data), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        employeeService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}