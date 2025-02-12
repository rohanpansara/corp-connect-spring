package com.corpConnect.dtos.hr;

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
public class DepartmentDTO extends NamedDTO {

    private NamedDTO departmentHead;
    private String code;
    private String location;
    private String phoneExtension;

}
