package com.corpConnect.security.dtos;

import com.corpConnect.dtos.user.UserDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PasswordDTO {

    private UserDTO user;
    private String password;
    private String confirmPassword;

}
