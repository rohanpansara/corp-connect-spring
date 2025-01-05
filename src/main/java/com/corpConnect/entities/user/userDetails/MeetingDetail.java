package com.corpConnect.entities.user.userDetails;

import com.corpConnect.entities.common.NameEntity;
import com.corpConnect.entities.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "USERS_MEETING_DETAILS")
public class MeetingDetail extends NameEntity {

    @Column(name = "user_id")
    private User user;

}
