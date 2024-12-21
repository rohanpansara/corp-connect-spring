package com.corpConnect.dtos.user.userDetails;

import com.corpConnect.dtos.common.BaseDTO;
import com.corpConnect.dtos.hr.LocationDTO;
import com.corpConnect.dtos.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PunchDetailDTO extends BaseDTO {

    private UserDTO userDTO;
    private LocalDateTime punchTime;
    private String punchType;
    private LocationDTO location;
    private String remarks;

}

