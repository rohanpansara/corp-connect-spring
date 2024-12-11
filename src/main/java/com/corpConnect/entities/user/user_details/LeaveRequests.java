package com.corpConnect.entities.user.user_details;

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
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USERS_LEAVE_REQUESTS")
public class LeaveRequests extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User requestedBy; // The user who requested the leave

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LeaveStatus status; // Current status of the leave request

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leave_type_id")
    private LeaveType type; // Type of leave requested

    private LocalDate startDate; // Start date of the leave
    private LocalDate endDate;   // End date of the leave

    private Integer totalDays; // Total number of leave days requested

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approving_manager_id")
    private User approvedBy; // Manager who approved or will approve the request
}

