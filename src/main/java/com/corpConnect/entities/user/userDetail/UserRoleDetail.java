package com.corpConnect.entities.user.userDetail;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.hr.Department;
import com.corpConnect.entities.hr.JobTitle;
import com.corpConnect.entities.hr.WorkShift;
import com.corpConnect.entities.user.User;
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
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USER_ROLE_DETAILS")
public class UserRoleDetail extends BaseEntity {

    @Column(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "userStatus", nullable = false)
    private UserStatus userStatus;

    @Column(name = "current_job_title_id")
    private JobTitle currentJobTitle;

    private LocalDate onboardingDate;

    @Column(name = "department_id")
    private Department department;

    @Column(name = "reporting_manager_id")
    private User reportingManager;

    private String probationPeriod;
    private LocalDate probationEndDate;

    @Column(name = "work_shift_id")
    private WorkShift workShift;

    private String currentProjects;
    private String currentJobResponsibilities;
    private String workLocation;

    private Boolean isDeleted;

}
