package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.EmployeeDto;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.model.Employee;
import com.vasconcellos.dailyreport.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee save(EmployeeDto data) {
        Employee employee = new Employee();

        employee.setName(data.getName());
        employee.setType(data.getType());

        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee cannot be found."));
    }

    public Employee findByName(String name) {
        return employeeRepository.findByName(name).orElseThrow(() ->
                new ResourceNotFoundException("Employee cannot be found."));
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}