package com.corpConnect.dtos.company;

import com.corpConnect.dtos.common.BaseDTO;
import com.corpConnect.dtos.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTPDTO extends BaseDTO {

    private String otp;
    private String type;
    private UserDTO user;
    private Boolean verified;

}
