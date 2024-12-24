package com.corpConnect.dtos.user.userDetails;

import com.corpConnect.dtos.common.BaseDTO;
import com.corpConnect.dtos.hr.DepartmentDTO;
import com.corpConnect.dtos.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDetailDTO extends BaseDTO {

    private UserDTO userDTO;
    private DepartmentDTO departmentDTO;

}
