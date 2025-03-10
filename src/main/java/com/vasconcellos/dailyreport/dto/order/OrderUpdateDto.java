package com.vasconcellos.dailyreport.dto.order;

import com.vasconcellos.dailyreport.model.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderUpdateDto {

    private String description;

    private OrderStatus status;
    private LocalDateTime endDate;

}