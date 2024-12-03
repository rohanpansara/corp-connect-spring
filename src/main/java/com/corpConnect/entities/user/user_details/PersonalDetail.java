package com.corpConnect.entities.user.user_details;

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
@Table(name = "USER_PERSONAL_DETAILS")
public class PersonalDetail extends BaseEntity {

    @Column(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    private LocalDate birthdate;
    private String contactNumber;
    private String address;
    private String personalEmail;
    private String emergencyContactName;
    private String emergencyContactNumber;
}
