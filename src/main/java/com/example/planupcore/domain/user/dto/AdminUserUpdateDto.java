package com.example.planupcore.domain.user.dto;

public record AdminUserUpdateDto(
    String email,

    String nickname,

    String firstName,

    String lastName
) {}
