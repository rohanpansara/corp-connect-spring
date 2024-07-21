package com.jwtauthentication.dtos.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

    private String status;      // Status of the response (e.g., "success", "error")
    private String message;     // Message providing details about the response
    private T data;             // The actual data to be returned (can be any type)


    public static <T> ResponseDTO<T> success(String message, T data) {
        return new ResponseDTO<>("success", message, data);
    }


    public static <T> ResponseDTO<T> error(String message, String errorCode) {
        return new ResponseDTO<>("error", message, null);
    }


    public static <T> ResponseDTO<T> of(String status, String message, T data) {
        return new ResponseDTO<>(status, message, data);
    }
}

