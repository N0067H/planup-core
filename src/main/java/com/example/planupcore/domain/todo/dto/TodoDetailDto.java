package com.example.planupcore.domain.todo.dto;

import com.example.planupcore.domain.todo.entity.Todo;

public record TodoDetailDto (
    String id,
    String title,
    String description,
    String status,
    String priority
) {
    public static TodoDetailDto fromEntity(Todo todo) {
        return new TodoDetailDto(
            todo.getId().toString(),
            todo.getTitle(),
            todo.getDescription(),
            todo.getStatus().name(),
            todo.getPriority().name()
        );
    }
}
