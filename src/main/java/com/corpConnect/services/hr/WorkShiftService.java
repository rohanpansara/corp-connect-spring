package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.WorkShiftDTO;
import com.corpConnect.entities.hr.WorkShift;

import java.util.List;

public interface WorkShiftService {

    // Holiday Mapper
    WorkShift getEntity(WorkShiftDTO workShiftDTO);
    WorkShiftDTO getDTO(WorkShift workShift);
    List<WorkShiftDTO> getDTOList(List<WorkShift> workShiftList);
    List<WorkShift> getEntityList(List<WorkShiftDTO> workShiftDTOList);

    // CRUD Operations
    void createWorkShift(WorkShiftDTO workShiftDTO);
    void updateWorkShift(Long oldWorkShiftId, WorkShiftDTO workShiftDTO);
    void deleteWorkShift(WorkShiftDTO workShiftDTO);
    void deleteWorkShiftById(Long workShiftId);

}
