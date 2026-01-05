package com.example.planupcore.global.advice;

public record ApiResponse<T>(
    boolean success,
    T data,
    String error
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static ApiResponse<Void> fail(String error) {
        return new ApiResponse<>(false, null, error);
    }
}
