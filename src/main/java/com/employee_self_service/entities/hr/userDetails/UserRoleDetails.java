package com.employee_self_service.entities.hr.userDetails;

import com.employee_self_service.entities.common.BaseEntity;
import com.employee_self_service.entities.hr.JobTitles;
import com.employee_self_service.entities.users.User;
import com.employee_self_service.enumerations.UserStatus;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USER_ROLE_DETAILS")
public class UserRoleDetails extends BaseEntity {

    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "userStatus", nullable = false)
    private UserStatus userStatus;
    private JobTitles currentJobTitle;
    private LocalDate onboardingDate;
    private String department;
    private String reportingManager;
    private String probationPeriod;
    private LocalDate probationEndDate;
    private String workShift;
    private String currentProjects;
    private String currentJobResponsibilities;
    private String workLocation;

}
