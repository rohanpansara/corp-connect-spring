package com.corpConnect.entities.user.userDetails;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.hr.JobTitle;
import com.corpConnect.entities.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "USER_DETAILS_EXPERIENCE")
public class ExperienceDetail extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    @Column(name = "previous_company")
    private String previousCompany;

    @Column(name = "previous_company_location")
    private String previousCompanyLocation;

    @Column(name = "previous_job_title")
    private String previousJobTitle;

    @Column(name = "previous_manager")
    private String previousManager;
    @Column(name = "previous_manager_contact_number")
    private String previousManagerContactNumber;

    @Column(name = "experience_years")
    private Float experienceYears;
    @Column(name = "job_responsibilities")
    private String jobResponsibilities;
    @Column(name = "technologies_worked_on")
    private String technologiesWorkedOn;

    @Column(name = "previous_job_start_date")
    private LocalDate previousJobStartDate;
    @Column(name = "previous_job_end_date")
    private LocalDate previousJobEndDate;

    @Column(name = "reason_for_leaving")
    private String reasonForLeaving;

    @Column(name = "considered_role_id")
    private JobTitle consideredRole;

}
