package com.vasconcellos.dailyreport.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderUpdateDescriptionDto {

    @NotNull
    private String description;

}