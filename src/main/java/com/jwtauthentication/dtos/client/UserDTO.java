package com.jwtauthentication.dtos.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jwtauthentication.dtos.common.NamedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends NamedDTO {
    private String name;
    private String email;
    private String roles;
    private boolean isAccountEnabled;
    private boolean isCredentialsNonExpired;
}
