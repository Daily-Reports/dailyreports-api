package com.vasconcellos.dailyreport.dto.order;

import com.vasconcellos.dailyreport.model.OrderSpeciality;
import com.vasconcellos.dailyreport.model.OrderStatus;
import com.vasconcellos.dailyreport.validation.NumberLength;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {

    private Long id;

    @NotNull
    @NumberLength(length = 10)
    private Long number;

    @NotNull
    private Long eventId;
    @NotNull
    private Long areaId;
    private Long subareaId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderSpeciality speciality;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotBlank
    @Size(min = 1, max = 100)
    private String description;
    @NotBlank
    private String technical;

    private LocalDateTime endDate;

}