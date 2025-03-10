package com.vasconcellos.dailyreport.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReportDto {

    private Long id;

    @NotNull
    private Long foremanId;
    @NotNull
    private Long supervisorId;

    private List<Long> notesIds;

}