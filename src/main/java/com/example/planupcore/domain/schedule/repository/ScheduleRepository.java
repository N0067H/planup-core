package com.example.planupcore.domain.schedule.repository;

import com.example.planupcore.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    Optional<Schedule> findByIdAndUserId(UUID scheduleId, UUID userId);
}
