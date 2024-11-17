package com.corpConnect.dtos.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.corpConnect.dtos.common.NamedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends NamedDTO {
    private String email;
    private String gender;
    private String roles;
    private List<String> permissions;
    private String isAccountEnabled;
    private String isCredentialsNonExpired;
    private String isAccountNonLocked;
    private String isAccountNonExpired;
}
