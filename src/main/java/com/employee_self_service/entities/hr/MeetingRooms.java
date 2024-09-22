package com.employee_self_service.entities.hr;

import com.employee_self_service.entities.users.User;
import com.employee_self_service.entities.common.NameWithDeleteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Table(name = "HR_MEETING_ROOMS")
public class MeetingRooms extends NameWithDeleteEntity {

    private String buildingName;
    private Integer floorNumber;
    private User pointOfContact;
    private Integer capacity;
    private String equipment;

}
