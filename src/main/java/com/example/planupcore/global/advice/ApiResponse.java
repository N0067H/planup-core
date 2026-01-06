package com.example.planupcore.global.advice;

import com.example.planupcore.global.exception.ErrorCode;

public record ApiResponse<T>(
    boolean success,
    T data,
    ErrorCode error,
    Object errors
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, null);
    }

    public static ApiResponse<Void> fail(ErrorCode error) {
        return new ApiResponse<>(false, null, error, null);
    }

    public static ApiResponse<Void> fail(ErrorCode error, Object errors) {
        return new ApiResponse<>(false, null, error, errors);
    }
}
