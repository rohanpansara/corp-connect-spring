package com.employee_self_service.dtos.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.employee_self_service.dtos.common.NamedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

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
