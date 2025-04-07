package org.dailyreports.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.employee.EmployeeDto;
import org.dailyreports.dto.employee.EmployeeUpdateDto;
import org.dailyreports.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto data) {
        return new ResponseEntity<>(employeeService.save(data), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable long id, @RequestBody EmployeeUpdateDto data) {
        return ResponseEntity.ok(employeeService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        employeeService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}