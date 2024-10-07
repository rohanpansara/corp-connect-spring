package com.corpConnect.entities.company;

import com.corpConnect.entities.common.NameWithDeleteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "COMPANY_CONFIGURATIONS")
public class Configuration extends NameWithDeleteEntity {

    private String configMaxValue;
    private String configMinValue;
    private boolean isEnabled = false;

}
