package com.corpConnect.entities.hr.userDetails;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.hr.Departments;
import com.corpConnect.entities.hr.JobTitles;
import com.corpConnect.entities.hr.WorkShifts;
import com.corpConnect.entities.users.Users;
import com.corpConnect.enumerations.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USER_ROLE_DETAILS")
public class UserRoleDetails extends BaseEntity {

    @Column(name = "user_id")
    private Users users;

    @Enumerated(EnumType.STRING)
    @Column(name = "userStatus", nullable = false)
    private UserStatus userStatus;

    @Column(name = "current_job_title_id")
    private JobTitles currentJobTitle;

    private LocalDate onboardingDate;

    @Column(name = "department_id")
    private Departments departments;

    @Column(name = "reporting_manager_id")
    private Users reportingManager;

    private String probationPeriod;
    private LocalDate probationEndDate;

    @Column(name = "work_shift_id")
    private WorkShifts workShift;

    private String currentProjects;
    private String currentJobResponsibilities;
    private String workLocation;

    private Boolean isDeleted;

}
