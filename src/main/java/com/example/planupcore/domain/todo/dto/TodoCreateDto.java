package com.example.planupcore.domain.todo.dto;

import com.example.planupcore.domain.todo.entity.TodoPriority;
import com.example.planupcore.domain.todo.entity.TodoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TodoCreateDto (
    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    String title,

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    String description,

    @NotNull(message = "Status is required")
    TodoStatus status,

    @NotNull(message = "Priority is required")
    TodoPriority priority
) {}
