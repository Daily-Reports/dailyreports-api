package com.vasconcellos.dailyreport.dto.note;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class NoteDto {

    private Long id;

    @NotNull
    private Long reportId;
    @NotNull
    private Long employeeId;
    @NotNull
    private Long orderId;

    @NotNull
    private LocalDateTime entryTime;
    @NotNull
    private LocalDateTime exitTime;

    private String comment;
    private List<String> imagesUrl;

}