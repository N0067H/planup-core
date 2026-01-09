package com.example.planupcore.domain.auth.service;

import com.example.planupcore.domain.auth.dto.LoginRequest;
import com.example.planupcore.domain.auth.dto.LoginResponse;
import com.example.planupcore.domain.token.entity.RefreshToken;
import com.example.planupcore.domain.token.repository.RefreshTokenRepository;
import com.example.planupcore.domain.user.dto.UserDetailDto;
import com.example.planupcore.domain.user.repository.UserRepository;
import com.example.planupcore.global.exception.ApiException;
import com.example.planupcore.global.exception.ErrorCode;
import com.example.planupcore.domain.token.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        var email = request.email();
        var password = request.password();

        var user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getHashedPassword())) {
            throw new ApiException(ErrorCode.INVALID_PASSWORD);
        }

        var userId = user.getId();

        var accessToken = jwtUtil.generateAccessToken(
            userId.toString(),
            user.getRole()
        );
        var refreshToken = jwtUtil.generateRefreshToken(
            userId.toString(),
            user.getRole()
        );

        var refreshTokenEntity = RefreshToken.create(
            userId,
            refreshToken,
            jwtUtil.getRefreshTokenExpiry()
        );

        refreshTokenRepository.save(refreshTokenEntity);

        return new LoginResponse(
            accessToken,
            refreshToken,
            UserDetailDto.fromEntity(user)
        );
    }

    @Transactional
    public void logout(String refreshToken) {
        var token = refreshTokenRepository.findByToken(refreshToken)
            .orElseThrow(() -> new ApiException(ErrorCode.INVALID_REFRESH_TOKEN));

        refreshTokenRepository.delete(token);
    }
}

