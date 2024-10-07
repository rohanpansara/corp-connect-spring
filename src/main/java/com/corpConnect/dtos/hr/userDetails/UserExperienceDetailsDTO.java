package com.corpConnect.dtos.hr.userDetails;

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
public class UserExperienceDetailsDTO extends BaseDTO {

    private UserDTO user;
    private String previousCompany;
    private String previousCompanyLocation;
    private String previousJobTitle;
    private String previousManager;
    private String previousManagerContact;
    private String experienceLevel;
    private String jobResponsibilities;
    private String technologiesWorkedOn;
    private String previousJobStartDate;
    private String previousJobEndDate;
    private String reasonForLeaving;
    private String consideredRole;

}
