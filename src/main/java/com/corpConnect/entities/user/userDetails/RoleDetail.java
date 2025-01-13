package com.corpConnect.entities.user.userDetails;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.hr.Department;
import com.corpConnect.entities.hr.JobTitle;
import com.corpConnect.entities.hr.WorkShift;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USER_DETAILS_ROLE")
public class RoleDetail extends BaseEntity {

    @Column(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Column(name = "current_job_title_id")
    private JobTitle currentJobTitle;

    @Column(name = "onboarding_date")
    private LocalDate onboardingDate;

    @Column(name = "department_id")
    private Department department;

    @Column(name = "reporting_manager_id")
    private User reportingManager;

    @Column(name = "probation_period")
    private String probationPeriod;
    @Column(name = "probation_end_date")
    private LocalDate probationEndDate;

    @Column(name = "work_shift_id")
    private WorkShift workShift;

    @Column(name = "current_projects")
    private String currentProjects;
    @Column(name = "current_job_responsibilities")
    private String currentJobResponsibilities;
    @Column(name = "work_location")
    private String workLocation;

    @Column(name = "referred_by")
    private User referredBy;

    private Boolean deleted;

}
