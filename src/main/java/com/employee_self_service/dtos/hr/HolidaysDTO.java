package com.employee_self_service.dtos.hr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.employee_self_service.dtos.common.NamedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HolidaysDTO extends NamedDTO {

    private String date;
    private String description;
    private String type;
    private boolean isRecurring;
}
