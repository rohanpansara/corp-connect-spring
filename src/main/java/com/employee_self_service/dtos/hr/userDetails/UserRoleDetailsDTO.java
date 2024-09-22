package com.employee_self_service.dtos.hr.userDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.employee_self_service.dtos.common.BaseDTO;
import com.employee_self_service.dtos.users.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleDetailsDTO extends BaseDTO {

    private UserDTO user;
    private String userStatus;
    private String currentJobTitleId;
    private String onboardingDate;
    private String department;
    private String reportingManager;
    private String probationPeriod;
    private String probationEndDate;
    private String workShift;
    private String currentProjects;
    private String currentJobResponsibilities;
    private String workLocation;

}
