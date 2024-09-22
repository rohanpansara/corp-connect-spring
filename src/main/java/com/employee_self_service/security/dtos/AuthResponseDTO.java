package com.employee_self_service.security.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.employee_self_service.dtos.users.UserDTO;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseDTO {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("logged_user")
    private UserDTO user;
}
