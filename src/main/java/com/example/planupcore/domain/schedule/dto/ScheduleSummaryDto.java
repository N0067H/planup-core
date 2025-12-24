package com.example.planupcore.domain.schedule.dto;

import com.example.planupcore.domain.schedule.Schedule;
import com.example.planupcore.domain.schedule.ScheduleType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleSummaryDto(
    UUID id,
    String title,
    LocalDateTime startTime,
    LocalDateTime endTime,
    ScheduleType scheduleType,
    boolean movable
) {
    public static ScheduleSummaryDto fromEntity(Schedule schedule) {
        return new ScheduleSummaryDto(
            schedule.getId(),
            schedule.getTitle(),
            schedule.getStartTime(),
            schedule.getEndTime(),
            schedule.getScheduleType(),
            schedule.isMovable()
        );
    }
}
