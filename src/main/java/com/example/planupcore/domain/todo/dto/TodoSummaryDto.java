package com.example.planupcore.domain.todo.dto;

import com.example.planupcore.domain.todo.entity.Todo;

public record TodoSummaryDto (
    String id,
    String title,
    String status,
    String priority
) {
    public static TodoSummaryDto fromEntity(Todo todo) {
        return new TodoSummaryDto(
            todo.getId().toString(),
            todo.getTitle(),
            todo.getStatus().name(),
            todo.getPriority().name()
        );
    }
}
