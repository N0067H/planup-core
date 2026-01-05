package com.example.planupcore.domain.schedule.exception;

import com.example.planupcore.global.exception.NotFoundException;

import java.util.UUID;

public class ScheduleNotFoundException extends NotFoundException {

    public ScheduleNotFoundException(UUID id) {
        super("Schedule not found: " + id);
    }
}
