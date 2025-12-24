package com.example.planupcore.domain.schedule.dto;

import com.example.planupcore.domain.schedule.ScheduleType;

import java.time.LocalDateTime;

public record CreateScheduleDto(
    String title,
    String description,
    LocalDateTime startTime,
    LocalDateTime endTime,
    ScheduleType scheduleType,
    boolean movable
) {}
