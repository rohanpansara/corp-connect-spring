package com.corpConnect.dtos.hr;

import com.corpConnect.dtos.common.NamedDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkShiftDTO extends NamedDTO {

    @Pattern(regexp = "^\\d{1,2}\\s?hrs\\s?\\d{1,2}\\s?mins$", message = "Duration must be in the format 'XXhrs XXmins'")
    private String duration;
    private String startTime;
    private String endTime;

}
