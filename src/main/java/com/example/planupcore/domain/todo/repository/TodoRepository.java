package com.example.planupcore.domain.todo.repository;

import com.example.planupcore.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
    Optional<Todo> findByIdAndUserId(UUID todoId, UUID userId);
}
