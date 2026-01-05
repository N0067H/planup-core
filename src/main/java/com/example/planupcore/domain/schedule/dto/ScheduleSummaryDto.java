package com.example.planupcore.domain.schedule.dto;

import com.example.planupcore.domain.schedule.entity.Schedule;
import com.example.planupcore.domain.schedule.entity.ScheduleType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleSummaryDto(
    UUID id,
    String title,
    LocalDateTime startTime,
    LocalDateTime endTime
) {
    public static ScheduleSummaryDto fromEntity(Schedule schedule) {
        return new ScheduleSummaryDto(
            schedule.getId(),
            schedule.getTitle(),
            schedule.getStartTime(),
            schedule.getEndTime()
        );
    }
}
