package com.jwtauthentication.dtos.hr;

import com.jwtauthentication.dtos.common.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class HolidayDTO extends BaseDTO {

    @NotBlank(message = "Holiday name must not be blank")
    private String holiday;
    private String date;
    private String description;
    private String type;
    private boolean isRecurring;
}
