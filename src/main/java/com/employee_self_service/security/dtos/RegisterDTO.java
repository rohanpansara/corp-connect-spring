package com.employee_self_service.security.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String roles;
    private String registerModule;
}
