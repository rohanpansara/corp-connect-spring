package com.corpConnect.entities.hr;

import com.corpConnect.entities.common.NameEntity;
import com.corpConnect.entities.common.NameWithDeleteEntity;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.MeetingStatus;
import com.corpConnect.enumerations.RecurrencePattern;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "HR_MEETINGS")
public class Meeting extends NameWithDeleteEntity {

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    @ManyToOne
    @JoinColumn(name = "meeting_room_id")
    private MeetingRoom meetingRoom;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MeetingStatus status;

    private boolean recurring;

    @Enumerated(EnumType.STRING)
    @Column(name = "recurrence_pattern", nullable = false)
    private RecurrencePattern recurrencePattern;

    private boolean virtual;

    private boolean secured;

    @Column(name = "access_code")
    private String accessCode;

}
