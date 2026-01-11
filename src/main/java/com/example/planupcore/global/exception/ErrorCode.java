package com.example.planupcore.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND),
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND),
    
    INVALID_NEW_PASSWORD(HttpStatus.BAD_REQUEST),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST),
    NO_UPDATE_FIELD(HttpStatus.BAD_REQUEST),
    INVALID_SCHEDULE_TIME(HttpStatus.BAD_REQUEST),

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED),

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;

    ErrorCode(HttpStatus status) {
        this.status = status;
    }
}
