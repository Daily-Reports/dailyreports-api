package org.dailyreports.dto.note;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteUpdateDto {

    private String comment;

}