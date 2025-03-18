package org.dailyreports.mapper;

import org.dailyreports.dto.EmployeeDto;
import org.dailyreports.model.Employee;
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