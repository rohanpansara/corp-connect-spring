package com.corpConnect.entities.user.userDetails;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.hr.LeaveType;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.LeaveStatus;
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

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USERS_LEAVE_DETAILS")
public class LeaveDetail extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user; // The user who requested the leave

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LeaveStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leave_type_id")
    private LeaveType type;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer totalDays;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approving_manager_id")
    private User approvedBy;

    private String remarks;
}

