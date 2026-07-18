package com.digitalbanking.api;

import com.digitalbanking.dtos.ErrorResponse;
import com.digitalbanking.exceptions.FunctionalError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FunctionalError.class)
    ResponseEntity<ErrorResponse> handleFunctionalError(Exception ex){
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage()).build()
        );
    }
}
