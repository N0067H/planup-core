package com.example.planupcore.domain.todo.service;

import com.example.planupcore.domain.todo.dto.TodoCreateDto;
import com.example.planupcore.domain.todo.dto.TodoDetailDto;
import com.example.planupcore.domain.todo.dto.TodoSummaryDto;
import com.example.planupcore.domain.todo.entity.Todo;
import com.example.planupcore.domain.todo.repository.TodoRepository;
import com.example.planupcore.global.exception.ApiException;
import com.example.planupcore.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoDetailDto createTodo(UUID userId, TodoCreateDto request) {
        var todo = Todo.create(
            userId,
            request.title(),
            request.description(),
            request.status(),
            request.priority()
        );

        var savedTodo = todoRepository.save(todo);
        return TodoDetailDto.fromEntity(savedTodo);
    }

    @Transactional(readOnly = true)
    public List<TodoSummaryDto> getAllTodos() {
        var todo = todoRepository.findAll();
        return todo.stream()
            .map(TodoSummaryDto::fromEntity)
            .toList();
    }

    @Transactional(readOnly = true)
    public TodoDetailDto getTodoById(UUID userId, UUID todoId) {
        var todo = todoRepository.findByIdAndUserId(userId, todoId)
            .orElseThrow(() -> new ApiException(ErrorCode.TODO_NOT_FOUND));
        return TodoDetailDto.fromEntity(todo);
    }

    @Transactional
    public TodoDetailDto updateTodo(UUID userId, UUID todoId, TodoCreateDto request) {
        var todo = todoRepository.findByIdAndUserId(todoId, userId)
            .orElseThrow(() -> new ApiException(ErrorCode.TODO_NOT_FOUND));

        var changed = false;

        if (request.title() != null) {
            todo.changeTitle(request.title());
            changed = true;
        }

        if (request.description() != null) {
            todo.changeDescription(request.description());
            changed = true;
        }

        if (request.status() != null) {
            todo.changeStatus(request.status());
            changed = true;
        }

        if (request.priority() != null) {
            todo.changePriority(request.priority());
            changed = true;
        }

        if (!changed) {
            throw new ApiException(ErrorCode.NO_UPDATE_FIELD);
        }

        return TodoDetailDto.fromEntity(todo);
    }

    @Transactional
    public void deleteTodo(UUID userId, UUID todoId) {
        var todo = todoRepository.findByIdAndUserId(todoId, userId)
            .orElseThrow(() -> new ApiException(ErrorCode.TODO_NOT_FOUND));
        todoRepository.delete(todo);
    }
}
