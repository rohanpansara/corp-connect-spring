package com.corpConnect.dtos.hr;

import com.corpConnect.dtos.common.NamedDTO;
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
public class MeetingDTO extends NamedDTO {

    private UserDTO organizer;
    private MeetingRoomDTO meetingRoom;

    private String startTime;
    private String endTime;

    private String description;
    private String status;

    private boolean recurring;
    private String recurrencePattern;

    private boolean virtual;
    private boolean secured;

    private String accessCode;
}
