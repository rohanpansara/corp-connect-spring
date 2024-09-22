package com.employee_self_service.dtos.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.employee_self_service.dtos.common.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyConfigurationsDTO extends BaseDTO {

    private String configName;
    private String configMaxValue;
    private String configMinValue;
    private boolean isEnabled;
    private boolean isDeleted;

}
