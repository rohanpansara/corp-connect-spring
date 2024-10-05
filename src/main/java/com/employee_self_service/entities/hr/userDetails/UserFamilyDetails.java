package com.employee_self_service.entities.hr.userDetails;

import com.employee_self_service.entities.common.BaseEntity;
import com.employee_self_service.entities.users.Users;
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
@Table(name = "USER_FAMILY_DETAILS")
public class UserFamilyDetails extends BaseEntity {

    @Column(name = "user_id")
    private Users users;

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
