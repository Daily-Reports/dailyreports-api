package org.dailyreports.dto.employee;

import lombok.Builder;
import lombok.Data;
import org.dailyreports.model.EmployeeType;

@Data
@Builder
public class EmployeeUpdateDto {

    private EmployeeType type;

}