package com.mealsapi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler({ResourceNotFoundException.class, NullPointerException.class})
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(buildErrorMessage("ResourceNotFoundException",
                exception.getMessage(),
                NOT_FOUND), NOT_FOUND);
    }

    private ErrorMessage buildErrorMessage(String error, String message, HttpStatus httpStatus) {
        return ErrorMessage.builder()
                .error(error)
                .message(message)
                .timeStamp(LocalDateTime.now())
                .httpStatus(httpStatus)
                .build();
    }


    @RequiredArgsConstructor
    @Builder
    @Getter
    @Setter
    private static class ErrorMessage {
        private final String error;
        private final String message;
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private final LocalDateTime timeStamp;
        private final HttpStatus httpStatus;
    }

}
