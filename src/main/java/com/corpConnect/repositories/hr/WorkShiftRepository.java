package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface WorkShiftRepository extends JpaRepository<WorkShift, Long> {

    WorkShift findByName(String name);

    List<WorkShift> findByStartTime(LocalTime startTime);
    List<WorkShift> findByEndTime(LocalTime endTime);

    List<WorkShift> findByDuration(String duration);
    List<WorkShift> findByDurationContaining(String durationPart); // For partial matches
    List<WorkShift> findByDurationGreaterThanEqual(String duration); // For minimum duration

}
