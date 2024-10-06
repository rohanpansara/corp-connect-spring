package com.corpConnect.dtos.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.corpConnect.dtos.common.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyConfigurationsDTO extends BaseDTO {

    private String configName;
    private String configMaxValue;
    private String configMinValue;
    private boolean isEnabled;
    private boolean isDeleted;

}
