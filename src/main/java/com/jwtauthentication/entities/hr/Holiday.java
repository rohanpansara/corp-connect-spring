package com.jwtauthentication.entities.hr;

import com.jwtauthentication.entities.common.NameWithDeleteEntity;
import com.jwtauthentication.entities.common.NamedEntity;
import com.jwtauthentication.enumerations.HolidayType;
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
@Table(name = "HOLIDAYS")
public class Holiday extends NameWithDeleteEntity {

    @Column(nullable = false)
    private LocalDate date;

    private String description;

    @Enumerated(EnumType.STRING)
    private HolidayType type;

    @Column(nullable = false)
    private boolean isRecurring = false;
}

