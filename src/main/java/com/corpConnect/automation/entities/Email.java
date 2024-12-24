package com.corpConnect.automation.entities;

import com.corpConnect.entities.common.NameWithDeleteEntity;
import com.corpConnect.entities.company.EmailTemplate;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Table(name = "AUTOMATION_EMAIL")
public class Email extends NameWithDeleteEntity {

    private LocalTime time;
    private String zoneIdRegion;
    private EmailTemplate emailTemplate;
    private LocalDate sendOnSpecificDate;
    private boolean sendDaily;
    private boolean sendMonthly;
    private boolean sendYearly;
    private boolean sendDuringHoliday;

}
