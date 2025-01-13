package com.corpConnect.entities.company;

import com.corpConnect.entities.common.NameWithDeleteEntity;
import jakarta.persistence.Column;
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
public class Configuration extends NameWithDeleteEntity {

    @Column(name = "max_val")
    private String maxVal;

    @Column(name = "min_val")
    private String minVal;

    private boolean enabled = false;

}
