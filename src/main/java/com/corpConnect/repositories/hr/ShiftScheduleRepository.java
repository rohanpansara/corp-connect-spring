package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.WorkShifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShiftScheduleRepository extends JpaRepository<WorkShifts, Long> {

    WorkShifts findByName(String name);

    List<WorkShifts> findByStartTime(LocalTime startTime);
    List<WorkShifts> findByEndTime(LocalTime endTime);

    List<WorkShifts> findByDuration(String duration);
    List<WorkShifts> findByDurationContaining(String durationPart); // For partial matches
    List<WorkShifts> findByDurationGreaterThanEqual(String duration); // For minimum duration

}
