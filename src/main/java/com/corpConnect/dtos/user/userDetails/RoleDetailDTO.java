package com.corpConnect.dtos.user.userDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.corpConnect.dtos.common.BaseDTO;
import com.corpConnect.dtos.user.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDetailDTO extends BaseDTO {

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
