package com.example.planupcore.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateDto (

    String email,

    String nickname,

    String firstName,

    String lastName,

    @NotBlank(message = "Password is required")
    String password,

    String newPassword
) {}
