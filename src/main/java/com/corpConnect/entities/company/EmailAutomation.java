package com.corpConnect.entities.company;

import com.corpConnect.entities.common.NameWithDeleteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "COMPANY_EMAIL_AUTOMATION")
public class EmailAutomation extends NameWithDeleteEntity {

    private LocalTime time;
    private String zoneIdRegion;
    private boolean sendDaily;
    private boolean sendMonthly;
    private boolean sendYearly;
    private boolean sendDuringHoliday;

}
