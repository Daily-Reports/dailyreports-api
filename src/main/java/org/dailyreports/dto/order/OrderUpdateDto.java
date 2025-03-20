package org.dailyreports.dto.order;

import lombok.Builder;
import lombok.Data;
import org.dailyreports.model.OrderStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderUpdateDto {

    private String description;

    private OrderStatus status;
    private LocalDateTime endDate;

}