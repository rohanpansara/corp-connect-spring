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

    private String usersLastName;

    private String fathersFirstName;
    private String fathersMiddleName;
    private String fathersLastName;
    private Integer fathersAge;
    private String fathersContactNumber;

    private String mothersFirstName;
    private String mothersMiddleName;
    private String mothersLastName;
    private Integer mothersAge;
    private String mothersContactNumber;

    private String brothersName;
    private Boolean isBrotherElder;
    private String brothersContactNumber;

    private String sistersName;
    private Boolean isSisterElder;
    private String sistersContactNumber;

}
