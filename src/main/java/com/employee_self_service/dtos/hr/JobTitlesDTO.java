package com.employee_self_service.dtos.hr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.employee_self_service.dtos.common.NamedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobTitlesDTO extends NamedDTO {

    private String grade;
    private String description;

}
