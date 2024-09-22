package com.employee_self_service.entities.hr.userDetails;

import com.employee_self_service.entities.common.BaseEntity;
import com.employee_self_service.entities.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Table(name = "USER_EXPERIENCE_DETAILS")
public class UserExperienceDetails extends BaseEntity {

    private User user;
    private String previousCompany;
    private String previousCompanyLocation;
    private String previousJobTitle;
    private String previousManager;
    private String previousManagerContact;
    private Float experienceYears;
    private String jobResponsibilities;
    private String technologiesWorkedOn;
    private LocalDate previousJobStartDate;
    private LocalDate previousJobEndDate;
    private String reasonForLeaving;
    private String consideredRole;

}
