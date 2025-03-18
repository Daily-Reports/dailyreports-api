package org.dailyreports.mapper;

import org.dailyreports.dto.ReportDto;
import org.dailyreports.model.Employee;
import org.dailyreports.model.Report;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ReportMapper {

    @Mapping(source = "data.id", target = "id")
    @Mapping(source = "foreman", target = "foreman")
    @Mapping(source = "supervisor", target = "supervisor")
    @Mapping(target = "notes", ignore = true)
    public abstract Report toEntity(ReportDto data, Employee foreman, Employee supervisor);

    @Mapping(source = "foreman.id", target = "foremanId")
    @Mapping(source = "supervisor.id", target = "supervisorId")
    @Mapping(target = "notesIds", expression = "java(report.getNotes().stream().map(note -> " +
            "note.getId()).collect(java.util.stream.Collectors.toList()))")
    public abstract ReportDto toDto(Report report);

}