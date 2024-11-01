package com.corpConnect.dtos.company;

import com.corpConnect.dtos.common.NamedDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigurationDTO extends NamedDTO {

    private String configMaxValue;
    private String configMinValue;
    private boolean isEnabled;
    private boolean isDeleted;

}
