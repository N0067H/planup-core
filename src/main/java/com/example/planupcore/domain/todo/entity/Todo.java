package com.example.planupcore.domain.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "todos")
@Getter
public class Todo {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "user_id", nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TodoStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private TodoPriority priority;

    protected Todo() {}

    private Todo(
        UUID userId,
        String title,
        String description,
        TodoStatus status,
        TodoPriority priority
    ) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public static Todo create(
        UUID userId,
        String title,
        String description,
        TodoStatus status,
        TodoPriority priority
    ) {
        return new Todo(userId, title, description, status, priority);
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeStatus(TodoStatus status) {
        this.status = status;
    }

    public void changePriority(TodoPriority priority) {
        this.priority = priority;
    }
}
