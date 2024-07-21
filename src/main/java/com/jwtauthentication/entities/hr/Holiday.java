package com.jwtauthentication.entities.hr;

import com.jwtauthentication.entities.common.NamedEntity;
import com.jwtauthentication.enumerations.HolidayType;
import jakarta.persistence.*;
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
@Table(name = "Holidays")
public class Holiday extends NamedEntity {

    @Column(nullable = false)
    private LocalDate date;

    private String description;

    @Enumerated(EnumType.STRING)
    private HolidayType type;

    @Column(nullable = false)
    private boolean isRecurring;
}

