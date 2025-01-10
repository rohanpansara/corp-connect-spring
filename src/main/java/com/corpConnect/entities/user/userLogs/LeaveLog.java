package com.corpConnect.entities.user.userLogs;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.hr.LeaveType;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.LeaveLogType;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USER_LOGS_LEAVE")
public class LeaveLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @Enumerated(EnumType.STRING)
    @Column(name = "leaveLogType", nullable = false)
    private LeaveLogType leaveLogType;

    private String remarks;

    private float leaveAdjustment;

    private float updatedBalance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "performed_by_id")
    private User performedBy;
}
