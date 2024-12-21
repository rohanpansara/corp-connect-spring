package com.corpConnect.entities.user.userDetails;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.AttendanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USER_DETAILS_ATTENDANCE")
public class AttendanceDetail extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AttendanceStatus attendanceStatus = AttendanceStatus.getDefault();

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal totalHours = BigDecimal.ZERO;

}
