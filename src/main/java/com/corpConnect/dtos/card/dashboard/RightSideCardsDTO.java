package com.corpConnect.dtos.card.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RightSideCardsDTO {
    private AttendanceAverageBaseCardDTO dailyAttendanceCard;
    private AttendanceAverageBaseCardDTO weeklyAttendanceCard;
}
