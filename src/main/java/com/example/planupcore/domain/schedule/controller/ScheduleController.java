package com.example.planupcore.domain.schedule.controller;

import com.example.planupcore.domain.schedule.dto.ScheduleUpdateDto;
import com.example.planupcore.domain.schedule.service.ScheduleService;
import com.example.planupcore.domain.schedule.dto.ScheduleCreateDto;
import com.example.planupcore.domain.schedule.dto.ScheduleDetailDto;
import com.example.planupcore.domain.schedule.dto.ScheduleSummaryDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleDetailDto> createSchedule(
        @AuthenticationPrincipal UUID userId,
        @RequestBody @Valid ScheduleCreateDto request
    ) {
        var schedule = scheduleService.createSchedule(userId, request);
        return ResponseEntity.status(201).body(schedule);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleSummaryDto>> getAllSchedules() {
        var summary = scheduleService.getAllSchedules();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDetailDto> getScheduleById(
        @PathVariable UUID scheduleId
    ) {
        var schedule = scheduleService.getScheduleById(scheduleId);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDetailDto> updateSchedule(
        @AuthenticationPrincipal UUID userId,
        @PathVariable UUID scheduleId,
        @RequestBody @Valid ScheduleUpdateDto request
    ) {
        var updated = scheduleService.updateSchedule(userId, scheduleId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
        @AuthenticationPrincipal UUID userId,
        @PathVariable UUID scheduleId
    ) {
        scheduleService.deleteSchedule(userId, scheduleId);
        return ResponseEntity.noContent().build();
    }
}
