package com.example.planupcore.domain.schedule;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "schedules")
@Getter
public class Schedule {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private ScheduleType scheduleType;

    @Column(nullable = false)
    private boolean movable;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected Schedule() {}

    private Schedule(
        UUID userId,
        String title,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ScheduleType scheduleType,
        boolean movable
    ) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduleType = scheduleType;
        this.movable = movable;
    };

    public static Schedule create(
        UUID userId,
        String title,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ScheduleType scheduleType,
        boolean movable
    ) {
        return new Schedule(
            userId,
            title,
            description,
            startTime,
            endTime,
            scheduleType,
            movable
        );
    }

    public void update(
        String title,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ScheduleType scheduleType,
        boolean movable
    ) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduleType = scheduleType;
        this.movable = movable;
    }
}
