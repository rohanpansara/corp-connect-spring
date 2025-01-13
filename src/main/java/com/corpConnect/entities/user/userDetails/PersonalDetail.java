package com.corpConnect.entities.user.userDetails;

import com.corpConnect.entities.common.BaseEntity;
import com.corpConnect.entities.user.User;
import com.corpConnect.enumerations.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USER_DETAILS_PERSONAL")
public class PersonalDetail extends BaseEntity {

    @Column(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    private LocalDate birthdate;
    @Column(name = "contact_number")
    private String contactNumber;
    private String address;
    @Column(name = "personal_email")
    private String personalEmail;
    @Column(name = "emergency_contact_name")
    private String emergencyContactName;
    @Column(name = "emergency_contact_number")
    private String emergencyContactNumber;
}
