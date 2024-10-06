package com.corpConnect.dtos.users.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeaveCardDTO {
    private CardDataDTO leaveTypes;
    private CardDataDTO leaveDetails;
}
