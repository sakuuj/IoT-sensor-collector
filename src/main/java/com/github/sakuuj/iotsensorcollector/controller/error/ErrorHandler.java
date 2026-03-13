package com.github.sakuuj.iotsensorcollector.controller.error;

import com.github.sakuuj.iotsensorcollector.exception.NotFoundException;
import com.github.sakuuj.iotsensorcollector.generator.InstantGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorHandler {

    private final InstantGenerator instantGenerator;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException notFoundException) {
        var apiError = ApiError.builder()
                .errorMessage(notFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(instantGenerator.generate())
                .build();

        return toResponse(apiError);
    }

    private static ResponseEntity<ApiError> toResponse(ApiError apiError) {

        return ResponseEntity.status(apiError.status())
                .body(apiError);
    }
}
