package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.EmployeeDto;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.mapper.EmployeeMapper;
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
    private final EmployeeMapper employeeMapper;

    public EmployeeDto save(EmployeeDto data) {
        Employee employee = employeeMapper.toEntity(data);

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeDto findById(Long id) {
        return employeeMapper.toDto(findByIdAsEntity(id));
    }

    public Employee findByIdAsEntity(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee cannot be found."));
    }

    public EmployeeDto findByName(String name) {
        return employeeRepository.findByName(name).map(employeeMapper::toDto).orElseThrow(() ->
                new ResourceNotFoundException("Employee cannot be found."));
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).toList();
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}