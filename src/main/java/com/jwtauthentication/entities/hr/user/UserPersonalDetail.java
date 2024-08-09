package com.jwtauthentication.entities.hr.user;

import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.entities.common.BaseEntity;
import com.jwtauthentication.enumerations.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
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
@Table(name = "HR_UserPersonalDetails")
@EntityListeners(AuditingEntityListener.class)
public class UserPersonalDetail extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @Length(min = 10, max = 10, message = "Contact Number should be of length 10")
    private String contactNumber;

    private Gender gender;
    private String bloodGroup;
}
