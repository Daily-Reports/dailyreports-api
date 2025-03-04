package com.vasconcellos.dailyreport.mapper;

import com.vasconcellos.dailyreport.dto.ReportDto;
import com.vasconcellos.dailyreport.model.Employee;
import com.vasconcellos.dailyreport.model.Report;
import com.vasconcellos.dailyreport.service.EmployeeService;
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

    @Named("mapEmployeeById")
    public Employee mapEmployeeById(Long id) {
        return employeeService.findById(id);
    }
}