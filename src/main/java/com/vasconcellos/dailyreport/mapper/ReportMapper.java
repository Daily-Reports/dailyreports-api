package com.vasconcellos.dailyreport.mapper;

import com.vasconcellos.dailyreport.dto.ReportDto;
import com.vasconcellos.dailyreport.exception.EmployeeNotFoundException;
import com.vasconcellos.dailyreport.exception.InvalidEmployeeException;
import com.vasconcellos.dailyreport.model.Employee;
import com.vasconcellos.dailyreport.model.EmployeeType;
import com.vasconcellos.dailyreport.model.Report;
import com.vasconcellos.dailyreport.service.EmployeeService;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ReportMapper {

    @Autowired
    private EmployeeService employeeService;

    @Mapping(source = "foremanId", target = "foreman", qualifiedByName = "mapEmployeeById")
    @Mapping(source = "supervisorId", target = "supervisor", qualifiedByName = "mapEmployeeById")
    @Mapping(target = "notes", ignore = true)
    public abstract Report toEntity(ReportDto orderDto);

    @Mapping(source = "foreman.id", target = "foremanId")
    @Mapping(source = "supervisor.id", target = "supervisorId")
    public abstract ReportDto toDto(Report report);

    @BeforeMapping
    public void validatedReport(ReportDto data) {
        validateEmployee(data.getForemanId(), EmployeeType.FOREMAN);
        validateEmployee(data.getSupervisorId(), EmployeeType.SUPERVISOR);
    }

    @Named("mapEmployeeById")
    public Employee mapEmployeeById(Long id) {
        return employeeService.findById(id).orElse(null);
    }

    private void validateEmployee(Long employeeId, EmployeeType expectedType) {
        Employee employee = mapEmployeeById(employeeId);

        if (employee == null)
            throw new EmployeeNotFoundException(expectedType.toString() + " cannot be found.");

        if (employee.getType() != expectedType)
            throw new InvalidEmployeeException("The specified " + expectedType.toString() + " has an invalid type.");
    }
}