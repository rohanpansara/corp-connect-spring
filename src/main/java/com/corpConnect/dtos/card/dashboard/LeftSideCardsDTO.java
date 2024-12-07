package com.corpConnect.dtos.card.dashboard;

import com.corpConnect.dtos.card.BaseCardDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeftSideCardsDTO {
    private BaseCardDTO leaveDetailsCard;
    private BaseCardDTO shiftDetailsCard;
    private MonthlyAttendanceCardDTO monthlyAttendanceCard;
}
