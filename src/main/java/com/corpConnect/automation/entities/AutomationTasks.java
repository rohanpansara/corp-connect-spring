package com.corpConnect.automation.entities;

import com.corpConnect.entities.common.NameWithDeleteEntity;
import com.corpConnect.entities.company.EmailTemplate;
import com.corpConnect.enumerations.AutomationType;
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
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "AUTOMATION_TASKS")
public class AutomationTasks extends NameWithDeleteEntity {

    private LocalTime time;
    private String zoneIdRegion;
    private String targetTopics;
    private EmailTemplate emailTemplate;
    private LocalDate sendOnSpecificDate;
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AutomationType type;

    private boolean sendDaily;
    private boolean sendMonthly;
    private boolean sendYearly;
    private boolean sendDuringHoliday;

}
