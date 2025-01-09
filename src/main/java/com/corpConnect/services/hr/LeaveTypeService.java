package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.LeaveTypeDTO;
import com.corpConnect.entities.hr.LeaveType;

import java.util.List;

public interface LeaveTypeService {

    // Leave Type Mapper
    LeaveType getEntity(LeaveTypeDTO leaveTypeDTO);
    LeaveTypeDTO getDTO(LeaveType leaveType);
    List<LeaveType> getEntityList(List<LeaveTypeDTO> leaveTypeDTOList);
    List<LeaveTypeDTO> getDTOList(List<LeaveType> leaveTypeList);

    // CRUD Operations
    void createLeaveType(LeaveTypeDTO leaveTypeDTO);
    void updateLeaveType(Long oldLeaveTypeId, LeaveTypeDTO leaveTypeDTO);
    void deleteLeaveType(LeaveTypeDTO leaveTypeDTO);
    void deleteLeaveTypeById(Long leaveTypeId);

    // Leave Type Repository
    List<LeaveType> getAllLeaveTypes();
    Integer getYearlyTotalLeaveCount();
}
