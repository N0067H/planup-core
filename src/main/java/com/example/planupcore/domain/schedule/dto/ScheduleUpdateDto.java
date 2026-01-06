package com.example.planupcore.domain.schedule.dto;

import java.time.LocalDateTime;

public record ScheduleUpdateDto(
    String title,
    String description,
    LocalDateTime startTime,
    LocalDateTime endTime
) {}
