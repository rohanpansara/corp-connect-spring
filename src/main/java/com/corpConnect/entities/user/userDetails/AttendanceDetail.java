package com.corpConnect.entities.user.userDetails;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.AttendancePeriod;
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
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "USER_DETAILS_ATTENDANCE")
public class AttendanceDetail extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;

    @Column(name = "first_punch_in")
    private LocalTime firstPunchIn;

    @Column(name = "last_punch_out")
    private LocalTime lastPunchOut;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AttendanceStatus attendanceStatus = AttendanceStatus.getDefault();

    @Enumerated(EnumType.STRING)
    @Column(name = "period", nullable = false)
    private AttendancePeriod attendancePeriod = AttendancePeriod.getDefault();

    @Column(name = "total_hours", precision = 5, scale = 2, nullable = false)
    private BigDecimal totalHours = BigDecimal.ZERO;

}

