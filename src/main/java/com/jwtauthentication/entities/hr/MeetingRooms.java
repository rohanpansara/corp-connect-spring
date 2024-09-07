package com.jwtauthentication.entities.hr;

import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.entities.common.NameWithDeleteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MEETING_ROOMS")
public class MeetingRooms extends NameWithDeleteEntity {
    @Column(name = "floor_number", nullable = false)
    private String floorNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_of_contact_id")
    private User pointOfContact;

    @Column(name = "capacity")
    private Integer capacity;

    @Lob
    @Column(name = "equipment")
    private String equipment;
}
