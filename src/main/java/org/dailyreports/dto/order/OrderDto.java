package org.dailyreports.dto.order;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.dailyreports.model.OrderSpeciality;
import org.dailyreports.model.OrderStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {

    private Long id;

    @NotNull
    private Long orderNumber;

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