package com.example.planupcore.domain.user.entity;

import jakarta.persistence.*;

import lombok.Getter;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@SoftDelete(columnName = "deleted_at")
@EntityListeners(AuditingEntityListener.class)
@Getter
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    protected User() {}

    private User(
        String email,
        String nickname,
        String firstName,
        String lastName,
        String hashedPassword,
        UserRole role
    ) {
        this.email = email;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public static User createUser(
        String email,
        String nickname,
        String firstName,
        String lastName,
        String hashedPassword
    ) {
        return new User(
            email,
            nickname,
            firstName,
            lastName,
            hashedPassword,
            UserRole.USER
        );
    }

    public static User createAdmin(
        String email,
        String nickname,
        String firstName,
        String lastName,
        String hashedPassword
    ) {
        return new User(
            email,
            nickname,
            firstName,
            lastName,
            hashedPassword,
            UserRole.ADMIN
        );
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void changeLastName(String lastName) {
        this.lastName = lastName;
    }

    public void changePassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
