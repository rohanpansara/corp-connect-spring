package com.corpConnect.entities.hr;

import com.corpConnect.entities.users.Users;
import com.corpConnect.entities.common.NameWithDeleteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "HR_MEETING_ROOMS")
public class MeetingRooms extends NameWithDeleteEntity {

    private String buildingName;
    private Integer floorNumber;

    @Column(name = "point_of_contact_id")
    private Users pointOfContact;

    private Integer capacity;
    private String equipment;

}

