package com.corpConnect.services.user.userDetails;

import com.corpConnect.dtos.user.userDetails.PunchDetailDTO;
import com.corpConnect.entities.user.User;
import com.corpConnect.entities.user.userDetails.PunchDetail;
import com.corpConnect.enumerations.PunchType;

import java.time.LocalDate;
import java.util.List;

public interface PunchDetailService {

    // Punch Detail Mapper
    PunchDetail getEntity(PunchDetailDTO punchDetailDTO);
    PunchDetailDTO getDTO(PunchDetail punchDetail);
    List<PunchDetail> getEntityList(List<PunchDetailDTO> punchDetailDTOList);
    List<PunchDetailDTO> getDTOList(List<PunchDetail> punchDetailsList);

    // CRUD Operations
    void createPunchDetail(PunchDetailDTO punchDetailDTO);
    void updatePunchDetail(Long punchDetailToUpdateId, PunchDetailDTO newPunchDetailDTO);
    PunchDetail getPunchDetailById(Long punchDetailId);
    void deletePunchDetail(PunchDetailDTO punchDetailDTO);
    void deletePunchDetailById(Long punchTitleId);

    // Punch Detail Repository
    List<PunchDetail> getPunchDetailByUserId(Long userId);
    List<PunchDetail> getPunchDetailByDate(LocalDate date);
    List<PunchDetail> getPunchDetailByUserIdAndDate(Long userId, LocalDate date);
    List<PunchDetail> getPunchDetailByDepartmentIdAndDate(Long departmentId, LocalDate date);
    List<PunchDetail> getPunchDetailByUserIdAndDateAndPunchType(Long userId, LocalDate date, PunchType punchType);

    // User Repository
    List<User> getUsersWithForbiddenPunchesByDate(LocalDate date);
    List<User> getUsersWithForbiddenPunchesByDepartmentIdAndDate(Long departmentId, LocalDate date);
}
