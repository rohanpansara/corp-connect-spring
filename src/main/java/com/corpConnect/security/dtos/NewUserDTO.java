package com.corpConnect.security.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class NewUserDTO {
    private String name;
    private String email;
    private String roles;
    private String registerModule;
}
