package com.corpConnect.dtos.hr;

import com.corpConnect.dtos.common.NamedDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HolidayDTO extends NamedDTO {

    private String date;
    private String description;
    private String type;
    private boolean isRecurring;
}
