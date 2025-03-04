package com.vasconcellos.dailyreport.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteUpdateCommentDto {

    @NotBlank
    private String comment;

}