package com.corpConnect.entities.user;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.hr.JobTitle;
import jakarta.persistence.Column;
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
public class ExperienceDetail extends BaseEntity {

    @Column(name = "user_id")
    private User user;

    private String previousCompany;
    private String previousCompanyLocation;

    private String previousJobTitle;

    private String previousManager;
    private String previousManagerContactNumber;

    private Float experienceYears;
    private String jobResponsibilities;
    private String technologiesWorkedOn;

    private LocalDate previousJobStartDate;
    private LocalDate previousJobEndDate;

    private String reasonForLeaving;

    @Column(name = "considered_role_id")
    private JobTitle consideredRole;

}