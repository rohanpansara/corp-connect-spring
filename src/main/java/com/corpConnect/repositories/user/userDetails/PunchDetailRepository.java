package com.corpConnect.repositories.user.userDetails;

import com.corpConnect.entities.user.User;
import com.corpConnect.entities.user.userDetails.PunchDetail;
import com.corpConnect.enumerations.PunchType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PunchDetailRepository extends JpaRepository<PunchDetail, Long> {

    List<PunchDetail> findByPunchTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<PunchDetail> findByPunchTime(LocalDateTime punchTime);

    List<PunchDetail> findByUser(User user);
    List<PunchDetail> findByUserId(Long userId);

    List<PunchDetail> findByUserIdInAndPunchTimeBetween(List<Long> userIdList, LocalDateTime startDate, LocalDateTime endDate);
    List<PunchDetail> findByUserIdAndPunchTimeBetweenAndPunchType(Long userId, LocalDateTime startDate, LocalDateTime endDate, PunchType punchType);

    List<User> findDistinctByAllowedFalseAndPunchTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT DISTINCT pd.user FROM PunchDetail pd " +
            "JOIN DepartmentDetail dd ON pd.user = dd.user " +
            "WHERE dd.department.id = :departmentId " +
            "AND pd.allowed = false " +
            "AND pd.punchTime BETWEEN :startOfDay AND :endOfDay")
    List<User> findUsersWithForbiddenPunchesByDepartmentIdAndDate(Long departmentId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<PunchDetail> findByPunchType(PunchType punchType);
    List<PunchDetail> findByAllowed(boolean allowed);
    List<PunchDetail> findByLocationId(Long locationId);

}
