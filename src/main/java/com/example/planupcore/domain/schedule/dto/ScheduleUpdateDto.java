package com.example.planupcore.domain.schedule.dto;

import com.example.planupcore.domain.schedule.entity.ScheduleType;

import java.time.LocalDateTime;

public record ScheduleUpdateDto(
    String title,
    String description,
    LocalDateTime startTime,
    LocalDateTime endTime,
    ScheduleType scheduleType,
    boolean movable
) {}
