package com.vasconcellos.dailyreport.dto;

import com.vasconcellos.dailyreport.model.EmployeeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeType type;

}