package org.dailyreports.mapper;

import org.dailyreports.dto.note.NoteDto;
import org.dailyreports.model.Employee;
import org.dailyreports.model.Note;
import org.dailyreports.model.Order;
import org.dailyreports.model.Report;
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