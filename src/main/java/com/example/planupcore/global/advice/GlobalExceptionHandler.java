package com.example.planupcore.global.advice;

import com.example.planupcore.global.dto.ApiResponse;
import com.example.planupcore.global.exception.ApiException;
import com.example.planupcore.global.exception.ErrorCode;
import com.example.planupcore.global.dto.FieldErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handle(ApiException e) {
        return ResponseEntity
            .status(e.getErrorCode().getStatus())
            .body(ApiResponse.fail(e.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handle(MethodArgumentNotValidException e) {

        var errors = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> new FieldErrorDto(
                err.getField(),
                err.getDefaultMessage()
            ))
            .toList();

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.fail(ErrorCode.VALIDATION_FAILED, errors));
    }
}

