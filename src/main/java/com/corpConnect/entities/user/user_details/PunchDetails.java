package com.corpConnect.entities.user.user_details;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.PunchType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USERS_PUNCH_DETAILS")
public class PunchDetails extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime punchTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "punchType", nullable = false)
    private PunchType punchType = PunchType.getDefault();

    private String location;
    private boolean isAllowed;
    private String remarks;

}
