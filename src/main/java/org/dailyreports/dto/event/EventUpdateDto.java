package org.dailyreports.dto.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventUpdateDto {

    private String name;

}