package com.vasconcellos.dailyreport.mapper;

import com.vasconcellos.dailyreport.dto.EmployeeDto;
import com.vasconcellos.dailyreport.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee area);

    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "reportsAsForeman", ignore = true)
    @Mapping(target = "reportsAsSupervisor", ignore = true)
    Employee toEntity(EmployeeDto data);

}