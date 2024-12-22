package com.corpConnect.exceptions;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.exceptions.client.LoginFailedException;
import com.corpConnect.exceptions.common.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles custom LoginFailedException.
     */
    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ResponseDTO<String>> handleLoginFailedException(LoginFailedException ex) {
        logWarning(ex);
        return buildResponse(ex.getMessage(), ex.getErrorCode(), HttpStatus.CONFLICT);
    }

    /**
     * Handles base custom exceptions.
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResponseDTO<String>> handleBaseException(BaseException ex) {
        logError(ex);
        return buildResponse(ex.getMessage(), ex.getErrorCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles Spring validation exceptions.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logWarning(ex);
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(ObjectError::getDefaultMessage)
                .orElse("Validation error occurred");
        return buildResponse(errorMessage, "VALIDATION_ERROR", HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all uncaught exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleGenericException(Exception ex) {
        logError(ex);
        String errorMessage = Optional.ofNullable(ex.getMessage())
                .orElse("An unexpected error occurred");
        return buildResponse(errorMessage, "GENERAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Utility method to construct the ResponseDTO for the API response.
     */
    private ResponseEntity<ResponseDTO<String>> buildResponse(String message, String errorCode, HttpStatus status) {
        ResponseDTO<String> response = ResponseDTO.error(message, errorCode);
        return new ResponseEntity<>(response, status);
    }

    /**
     * Logs an error-level exception.
     */
    private void logError(Exception ex) {
        logger.error("Exception caught: {} - {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
    }

    /**
     * Logs a warning-level exception.
     */
    private void logWarning(Exception ex) {
        logger.warn("Exception caught: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
    }
}