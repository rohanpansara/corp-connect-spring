package com.jwtauthentication.dtos.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String name;
    private String email;
    private String roles;
    private boolean isAccountEnabled;
    private boolean isAccountLocked;
}
