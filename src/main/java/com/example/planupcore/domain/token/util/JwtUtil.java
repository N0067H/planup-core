package com.example.planupcore.domain.token.util;

import com.example.planupcore.domain.user.entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessTokenValidity;
    private final Duration refreshTokenValidity;

    public JwtUtil(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.access-expiration-minutes}") long accessExpirationMinutes,
        @Value("${jwt.refresh-expiration-days}") long refreshExpirationDays
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidity = Duration.ofMinutes(accessExpirationMinutes);
        this.refreshTokenValidity = Duration.ofDays(refreshExpirationDays);
    }

    public String generateAccessToken(String userId, UserRole role) {
        return Jwts.builder()
            .subject(userId)
            .signWith(secretKey)
            .claim("role", role)
            .expiration(new Date(
                System.currentTimeMillis() + accessTokenValidity.toMillis()
            ))
            .compact();
    }

    public String generateRefreshToken(String userId, UserRole role) {
        return Jwts.builder()
            .subject(userId)
            .signWith(secretKey)
            .claim("role", role)
            .expiration(new Date(
                System.currentTimeMillis() + refreshTokenValidity.toMillis()
            ))
            .compact();
    }

    public Claims parseAccessToken(String token) {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public LocalDateTime getRefreshTokenExpiry() {
        return LocalDateTime.now().plus(refreshTokenValidity);
    }

}
