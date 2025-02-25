package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.EmployeeDto;
import com.vasconcellos.dailyreport.model.Employee;
import com.vasconcellos.dailyreport.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}