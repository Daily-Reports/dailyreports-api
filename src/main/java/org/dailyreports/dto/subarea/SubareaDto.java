package org.dailyreports.dto.subarea;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubareaDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

}