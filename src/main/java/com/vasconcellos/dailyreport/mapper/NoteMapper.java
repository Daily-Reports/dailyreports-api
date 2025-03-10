package com.vasconcellos.dailyreport.mapper;

import com.vasconcellos.dailyreport.dto.note.NoteDto;
import com.vasconcellos.dailyreport.model.Employee;
import com.vasconcellos.dailyreport.model.Note;
import com.vasconcellos.dailyreport.model.Order;
import com.vasconcellos.dailyreport.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(source = "data.id", target = "id")
    @Mapping(source = "report", target = "report")
    @Mapping(source = "employee", target = "employee")
    @Mapping(source = "order", target = "order")
    Note toEntity(NoteDto data, Report report, Employee employee, Order order);

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "report.id", target = "reportId")
    NoteDto toDto(Note note);

}