package com.jwtauthentication.dtos.client.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {
    private String titleOne;
    private String contentOne;
    private String titleTwo;
    private String contentTwo;
}
