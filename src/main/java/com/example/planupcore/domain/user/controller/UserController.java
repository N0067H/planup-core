package com.example.planupcore.domain.user.controller;

import com.example.planupcore.domain.user.dto.*;
import com.example.planupcore.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDetailDto> createUser(
        @RequestBody @Valid UserCreateDto request
    ) {
        var user = userService.createUser(request);
        return ResponseEntity.status(201).body(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserSummaryDto>> getAllUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDetailDto> getUserById(
        @PathVariable UUID id
    ) {
        var user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailDto> getCurrentUser(
        @AuthenticationPrincipal UUID userId
    ) {
        var user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<UserDetailDto> updateUser(
        @AuthenticationPrincipal UUID userId,
        @RequestBody @Valid UserUpdateDto request
    ) {
        var updatedUser = userService.updateUser(userId, request);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDetailDto> updateUserByAdmin(
        @PathVariable UUID id,
        @RequestBody @Valid AdminUserUpdateDto request
    ) {
        var updatedUser = userService.updateUserByAdmin(id, request);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
        @AuthenticationPrincipal UUID userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserByAdmin(
        @PathVariable UUID id
    ) {
        userService.deleteUserByAdmin(id);
        return ResponseEntity.noContent().build();
    }

}
