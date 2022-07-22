package com.thor.storage.exception;

import com.thor.storage.dto.ExceptionFieldError;
import com.thor.storage.dto.ExceptionResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static java.util.List.of;

@ControllerAdvice
@Log4j2
public class StorageExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ExceptionResponse> handleBusinessException
            (final BusinessException ex) {
        log.error("There was a business error: {}", ex.getError());
        return this.getExceptionResponse(ex.getStatus(),  ex.getError());
    }

    private ResponseEntity<ExceptionResponse> getExceptionResponse
            (final HttpStatus status, final String message) {
        final var response = ExceptionResponse.builder()
                .messages(of(ExceptionFieldError.builder()
                        .error(message)
                        .build()))
                .status(status.value())
                .build();
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<ExceptionResponse> getExceptionResponse
            (final HttpStatus status, final List<ExceptionFieldError> messages) {
        final var response = ExceptionResponse.builder()
                .messages(messages)
                .status(status.value())
                .build();
        return ResponseEntity.status(status).body(response);
    }
}
