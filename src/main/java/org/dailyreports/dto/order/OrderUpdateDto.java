package org.dailyreports.dto.order;

import lombok.Builder;
import lombok.Data;
import org.dailyreports.model.OrderSpeciality;
import org.dailyreports.model.OrderStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderUpdateDto {

    private String technical;
    private String description;

    private Long eventId;
    private Long areaId;
    private Long subareaId;

    private OrderSpeciality speciality;

    private OrderStatus status;
    private LocalDateTime endDate;

}