package com.corpConnect.entities.hr;

import com.corpConnect.entities.common.NameEntity;
import com.corpConnect.enumerations.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "HR_LEAVE_TYPE")
public class LeaveType extends NameEntity {
    private boolean isPaidLeave;                // Indicates if the leave is paid or unpaid
    private Integer maxLeavesPerMonth;          // Maximum number of leaves a user can take in a month
    private Integer maxLeavesPerYear;           // Maximum number of leaves a user can take in a year
    private Integer maxLeaveRequestsPerMonth;   // Maximum number of leave requests a user can submit in a month
    private Integer maxLeaveRequestsPerYear;    // Maximum number of leave requests a user can submit in a year
    private Integer monthlyAccruedLeaves;       // Number of leaves accrued each month
    private boolean canCarryForward;            // Indicates if unused leaves can be carried forward to the next year
    private Integer maxCarryForwardLeaves;      // Maximum number of leaves that can be carried forward
    private boolean isHalfDayLeaveAllowed;      // Indicates if half-day leave is permitted
    private boolean canHaveNegativeBalance;     // Indicates if leave requests can result in a negative balance
    private Integer priorNoticeDaysRequired;    // Number of days prior notice required to apply for this leave type
    private boolean isAllowedDuringProbation;   // Indicates if the leave type can be availed during probation period
    private boolean isDocumentRequired;         // Indicates if supporting documents are required for this leave type
    private Gender applicableGender;            // Specifies if the leave type is restricted to a specific gender
}

