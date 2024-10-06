package com.corpConnect.dtos.hr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.corpConnect.dtos.common.NamedDTO;
import com.corpConnect.dtos.users.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeetingRoomsDTO extends NamedDTO {

    private String floorNumber;
    private UserDTO pointOfContactId;
    private Integer capacity;
    private String equipment;

}
