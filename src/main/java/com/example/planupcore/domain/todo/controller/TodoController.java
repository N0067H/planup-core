package com.example.planupcore.domain.todo.controller;

import com.example.planupcore.domain.todo.dto.TodoCreateDto;
import com.example.planupcore.domain.todo.dto.TodoDetailDto;
import com.example.planupcore.domain.todo.dto.TodoSummaryDto;
import com.example.planupcore.domain.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDetailDto> createTodo(
        @AuthenticationPrincipal UUID userId,
        @Valid @RequestBody TodoCreateDto request
    ) {
        var todo = todoService.createTodo(userId, request);
        return ResponseEntity.status(201).body(todo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<TodoSummaryDto>> getAllTodos() {
        var todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDetailDto> getTodoById(
        @AuthenticationPrincipal UUID userId,
        @PathVariable UUID todoId
    ) {
        var todo = todoService.getTodoById(userId, todoId);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDetailDto> updateTodo(
        @AuthenticationPrincipal UUID userId,
        @PathVariable UUID todoId,
        @Valid @RequestBody TodoCreateDto request
    ) {
        var updatedTodo = todoService.updateTodo(userId, todoId, request);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(
        @AuthenticationPrincipal UUID userId,
        @PathVariable UUID todoId
    ) {
        todoService.deleteTodo(userId, todoId);
        return ResponseEntity.noContent().build();
    }
}
