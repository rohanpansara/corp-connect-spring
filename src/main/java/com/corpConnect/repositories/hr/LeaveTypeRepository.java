package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

    List<LeaveType> findByNameContaining(String name);
    List<LeaveType> findByIsPaidLeave(boolean isPaidLeave);
    List<LeaveType> findByCanCarryForward(boolean canCarryForward);
    List<LeaveType> findByIsHalfDayLeaveAllowed(boolean isHalfDayLeaveAllowed);
    List<LeaveType> findByCanHaveNegativeBalance(boolean canHaveNegativeBalance);
    List<LeaveType> findByIsAllowedDuringProbation(boolean isAllowedDuringProbation);
    List<LeaveType> findByIsDocumentRequired(boolean isDocumentRequired);

}

