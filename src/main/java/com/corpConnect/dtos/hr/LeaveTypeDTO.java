package com.corpConnect.dtos.hr;

import com.corpConnect.dtos.common.NamedDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeaveTypeDTO extends NamedDTO {
    private Boolean isPaidLeave;
    private Integer maxLeavesPerMonth;
    private Integer maxLeavesPerYear;
    private Integer maxLeaveRequestsPerMonth;
    private Integer maxLeaveRequestsPerYear;
    private Integer monthlyAccruedLeaves;
    private Boolean canCarryForward;
    private Integer maxCarryForwardLeaves;
    private Boolean isHalfDayLeaveAllowed;
    private Boolean canHaveNegativeBalance;
    private Integer priorNoticeDaysRequired;
    private Boolean isAllowedDuringProbation;
    private Boolean isDocumentRequired;
    private String applicableGender;
}
