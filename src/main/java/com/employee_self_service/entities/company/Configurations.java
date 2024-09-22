package com.employee_self_service.entities.company;

import com.employee_self_service.entities.common.NameWithDeleteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "COMPANY_CONFIGURATIONS")
public class Configurations extends NameWithDeleteEntity {

    private String configMaxValue;
    private String configMinValue;
    private boolean isEnabled = true;

}
