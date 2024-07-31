package com.jwtauthentication.dtos.hr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jwtauthentication.dtos.client.UserDTO;
import com.jwtauthentication.dtos.common.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPersonalDetailsDTO extends BaseDTO {

    @NotBlank(message = "Address must not be blank")
    private String address;

    @Length(min = 10, max = 10, message = "Contact Number should be of length 10")
    private String contactNumber;

    private String gender;
    private String bloodGroup;

    private UserDTO user;
}
