package com.jwtauthentication.entities.hr;

import com.jwtauthentication.entities.client.User;
import com.jwtauthentication.entities.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserPersonalDetails extends BaseEntity {

    @Column(name = "user", nullable = false, unique = true)
    private User user;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @Length(min = 10, max = 10, message = "Contact Number should be of length 10")
    private String contactNumber;

    private String gender;
    private String bloodGroup;
}
