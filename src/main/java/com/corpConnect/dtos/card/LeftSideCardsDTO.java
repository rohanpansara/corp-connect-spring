package com.corpConnect.dtos.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeftSideCardsDTO {
    private CardDataDTO leaveDetailsCard;
    private CardDataDTO shiftDetailsCard;
}