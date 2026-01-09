package com.example.planupcore.domain.auth.dto;

import com.example.planupcore.domain.user.dto.UserDetailDto;

public record LoginResponse(
    String accessToken,
    String refreshToken,
    UserDetailDto user
) { }
