package com.jwtauthentication.dtos.hr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jwtauthentication.dtos.common.BaseDTO;
import com.jwtauthentication.dtos.common.NamedDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HolidayDTO extends NamedDTO {

    private String date;
    private String description;
    private String type;
    private boolean isRecurring;
}
