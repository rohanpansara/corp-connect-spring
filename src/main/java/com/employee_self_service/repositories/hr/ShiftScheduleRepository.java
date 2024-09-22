package com.employee_self_service.repositories.hr;

import com.employee_self_service.entities.hr.ShiftSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShiftScheduleRepository extends JpaRepository<ShiftSchedule, Long> {

    ShiftSchedule findByName(String name);

    List<ShiftSchedule> findByStartTime(LocalTime startTime);
    List<ShiftSchedule> findByEndTime(LocalTime endTime);

    List<ShiftSchedule> findByDuration(String duration);
    List<ShiftSchedule> findByDurationContaining(String durationPart); // For partial matches
    List<ShiftSchedule> findByDurationGreaterThanEqual(String duration); // For minimum duration

}
