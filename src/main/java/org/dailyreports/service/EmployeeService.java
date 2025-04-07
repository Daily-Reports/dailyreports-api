package org.dailyreports.service;

import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.employee.EmployeeDto;
import org.dailyreports.dto.employee.EmployeeUpdateDto;
import org.dailyreports.exception.ResourceNotFoundException;
import org.dailyreports.mapper.EmployeeMapper;
import org.dailyreports.model.Employee;
import org.dailyreports.repository.EmployeeRepository;
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

    public EmployeeDto update(long id, EmployeeUpdateDto data) {
        Employee employee = findByIdAsEntity(id);

        if(data.getType() != null)
            employee.setType(data.getType());

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