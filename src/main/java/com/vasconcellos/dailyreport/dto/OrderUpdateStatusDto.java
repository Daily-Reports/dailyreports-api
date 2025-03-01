package com.vasconcellos.dailyreport.dto;

import com.vasconcellos.dailyreport.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderUpdateStatusDto {

    @NotNull
    private OrderStatus status;
    private LocalDateTime endDate;

}