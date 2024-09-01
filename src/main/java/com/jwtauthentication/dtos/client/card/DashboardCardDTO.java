package com.jwtauthentication.dtos.client.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardCardDTO {
    private CardDataDTO leaveDetailsCard;
    private CardDataDTO dailyHoursCard;
    private CardDataDTO shiftDetailsCard;
    private CardDataDTO upcomingMeetingCard;
}
