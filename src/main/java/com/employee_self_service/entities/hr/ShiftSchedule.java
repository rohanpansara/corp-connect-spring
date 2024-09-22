package com.employee_self_service.entities.hr;

import com.employee_self_service.entities.common.NamedEntity;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "HR_SHIFT_SCHEDULES")
public class ShiftSchedule extends NamedEntity {

    private String duration;
    private LocalTime startTime;
    private LocalTime endTime;

}
