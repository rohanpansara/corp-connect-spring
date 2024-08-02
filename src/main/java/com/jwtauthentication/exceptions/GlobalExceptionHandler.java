package com.jwtauthentication.exceptions;

import com.jwtauthentication.dtos.common.ResponseDTO;
import com.jwtauthentication.exceptions.client.LoginFailedException;
import com.jwtauthentication.exceptions.common.BaseException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseDTO<String>> handleEmailAlreadyExistsException(LoginFailedException ex) {
        return buildResponse(ex.getMessage(), ex.getErrorCode(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO<String>> handleBadRequestException(BadRequestException ex) {
        return buildResponse(ex.getMessage(), "400", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResponseDTO<String>> handleBaseException(BaseException ex) {
        return buildResponse(ex.getMessage(), ex.getErrorCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleException(Exception ex) {
        return buildResponse(ex.getMessage(), "GENERAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ResponseDTO<String>> buildResponse(String message, String errorCode, HttpStatus status) {
        ResponseDTO<String> response = ResponseDTO.error(message, errorCode);
        return new ResponseEntity<>(response, status);
    }
}

