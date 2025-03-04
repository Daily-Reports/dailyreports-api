package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.ReportDto;
import com.vasconcellos.dailyreport.exception.InvalidEmployeeException;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.mapper.ReportMapper;
import com.vasconcellos.dailyreport.model.Employee;
import com.vasconcellos.dailyreport.model.EmployeeType;
import com.vasconcellos.dailyreport.model.Report;
import com.vasconcellos.dailyreport.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public Report save(ReportDto data) {
        Report toSave = reportMapper.toEntity(data);

        validateEmployee(toSave.getForeman(), EmployeeType.FOREMAN);
        validateEmployee(toSave.getForeman(), EmployeeType.SUPERVISOR);

        return reportRepository.save(toSave);
    }

    private void validateEmployee(Employee employee, EmployeeType expectedType) {
        if (employee == null)
            throw new ResourceNotFoundException(expectedType.toString() + " cannot be found.");

        if (employee.getType() != expectedType)
            throw new InvalidEmployeeException("The specified " + expectedType.toString() + " has an invalid type.");
    }

    public Report findById(Long id) {
        return reportRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Report cannot be found."));
    }

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public void deleteById(Long id) {
        reportRepository.deleteById(id);
    }
}