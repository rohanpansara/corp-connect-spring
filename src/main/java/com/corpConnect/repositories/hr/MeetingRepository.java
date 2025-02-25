package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.Meeting;
import com.corpConnect.enumerations.MeetingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findAllByOrganizerId(Long organizerId);
    List<Meeting> findAllByMeetingRoomId(Long meetingRoomId);
    List<Meeting> findAllByStatusIn(List<MeetingStatus> meetingStatuses);

    List<Meeting> findAllByStartTime(LocalDateTime startTime);
    List<Meeting> findAllByEndTime(LocalDateTime endTime);
    List<Meeting> findAllByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Meeting> findAllByEndTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Meeting> findAllByStartTimeAfterAndEndTimeBefore(LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT m FROM Meeting m WHERE (m.startTime < :endTime AND m.endTime > :startTime)")
    List<Meeting> findMeetingsOverlapping(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}
