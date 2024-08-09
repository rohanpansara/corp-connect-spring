package com.jwtauthentication.entities.hr;

import com.jwtauthentication.entities.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HR_ShiftSchedule")
@EntityListeners(AuditingEntityListener.class)
public class ShiftSchedule extends BaseEntity {
    private String type;
    private String duration;
    private LocalTime startTime;
    private LocalTime endTime;
}
