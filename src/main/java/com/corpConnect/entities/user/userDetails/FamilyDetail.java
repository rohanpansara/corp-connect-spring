package com.corpConnect.entities.user.userDetails;

import com.corpConnect.entities.common.BaseEntity;
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
@Table(name = "USER_DETAILS_FAMILY")
public class FamilyDetail extends BaseEntity {

    @Column(name = "user_id")
    private User user;

    @Column(name = "users_last_name")
    private String usersLastName;

    @Column(name = "fathers_first_name")
    private String fathersFirstName;
    @Column(name = "fathers_middle_name")
    private String fathersMiddleName;
    @Column(name = "fathers_last_name")
    private String fathersLastName;
    @Column(name = "fathers_age")
    private Integer fathersAge;
    @Column(name = "fathers_contact_number")
    private String fathersContactNumber;

    @Column(name = "mothers_first_name")
    private String mothersFirstName;
    @Column(name = "mothers_middle_name")
    private String mothersMiddleName;
    @Column(name = "mothers_last_name")
    private String mothersLastName;
    @Column(name = "mothers_age")
    private Integer mothersAge;
    @Column(name = "mothers_contact_number")
    private String mothersContactNumber;

    @Column(name = "brothers_name")
    private String brothersName;
    @Column(name = "is_brother_older")
    private Boolean isBrotherOlder;
    @Column(name = "brothers_contact_number")
    private String brothersContactNumber;

    @Column(name = "sisters_name")
    private String sistersName;
    @Column(name = "is_sister_older")
    private Boolean isSisterOlder;
    @Column(name = "sisters_contact_number")
    private String sistersContactNumber;

}
