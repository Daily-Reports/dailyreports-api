package org.dailyreports.service;

import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.ReportDto;
import org.dailyreports.exception.InvalidEmployeeException;
import org.dailyreports.exception.ResourceNotFoundException;
import org.dailyreports.mapper.ReportMapper;
import org.dailyreports.model.Employee;
import org.dailyreports.model.EmployeeType;
import org.dailyreports.model.Report;
import org.dailyreports.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    private final EmployeeService employeeService;

    public ReportDto save(ReportDto data) {
        Employee foreman = employeeService.findByIdAsEntity(data.getForemanId());
        Employee supervisor = employeeService.findByIdAsEntity(data.getSupervisorId());

        validateEmployee(foreman, EmployeeType.FOREMAN);
        validateEmployee(supervisor, EmployeeType.SUPERVISOR);

        Report report = reportMapper.toEntity(data, foreman, supervisor);
        return reportMapper.toDto(reportRepository.save(report));
    }

    private void validateEmployee(Employee employee, EmployeeType expectedType) {
        if (employee == null)
            throw new ResourceNotFoundException(expectedType.toString() + " cannot be found.");

        if (employee.getType() != expectedType)
            throw new InvalidEmployeeException("The specified " + expectedType.toString() + " has an invalid type.");
    }

    public ReportDto findById(Long id) {
        return reportMapper.toDto(findByIdAsEntity(id));
    }

    public Report findByIdAsEntity(Long id) {
        return reportRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Report cannot be found."));
    }

    public List<ReportDto> findAll() {
        return reportRepository.findAll().stream().map(reportMapper::toDto).toList();
    }

    public void deleteById(Long id) {
        reportRepository.deleteById(id);
    }
}