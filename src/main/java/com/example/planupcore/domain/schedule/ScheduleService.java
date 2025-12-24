package com.example.planupcore.domain.schedule;

import com.example.planupcore.domain.schedule.dto.CreateScheduleDto;
import com.example.planupcore.domain.schedule.dto.ScheduleDetailDto;
import com.example.planupcore.domain.schedule.dto.ScheduleSummaryDto;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/*

    @TODO: Better exception handling

*/

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleDetailDto saveSchedule(UUID userId, CreateScheduleDto dto) {
        var schedule = Schedule.create(
            userId,
            dto.title(),
            dto.description(),
            dto.startTime(),
            dto.endTime(),
            dto.scheduleType(),
            dto.movable()
        );

        var newSchedule = scheduleRepository.save(schedule);
        return ScheduleDetailDto.fromEntity(newSchedule);
    }

    @Transactional(readOnly = true)
    public ScheduleDetailDto getScheduleDetail(Long scheduleId) {
        var schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + scheduleId));
        return ScheduleDetailDto.fromEntity(schedule);
    }

    @Transactional(readOnly = true)
    public ScheduleSummaryDto getScheduleSummary(Long scheduleId) {
        var schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + scheduleId));
        return ScheduleSummaryDto.fromEntity(schedule);
    }

    @Transactional
    public ScheduleDetailDto updateSchedule(Long scheduleId, CreateScheduleDto dto) {
        var schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + scheduleId));

        schedule.update(
            dto.title(),
            dto.description(),
            dto.startTime(),
            dto.endTime(),
            dto.scheduleType(),
            dto.movable()
        );

        var updatedSchedule = scheduleRepository.save(schedule);
        return ScheduleDetailDto.fromEntity(updatedSchedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new RuntimeException("Schedule not found with id: " + scheduleId);
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
