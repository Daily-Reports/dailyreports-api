package org.dailyreports.dto.order;

import org.dailyreports.model.OrderStatus;
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