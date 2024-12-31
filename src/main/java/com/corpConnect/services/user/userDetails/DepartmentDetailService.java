package com.corpConnect.services.user.userDetails;

import com.corpConnect.dtos.user.userDetails.DepartmentDetailDTO;
import com.corpConnect.entities.user.userDetails.DepartmentDetail;

import java.util.List;

public interface DepartmentDetailService {

    // Department Detail Mapper
    DepartmentDetail getEntity(DepartmentDetailDTO departmentDetailDTO);
    DepartmentDetailDTO getDTO(DepartmentDetail departmentDetail);
    List<DepartmentDetail> getEntityList(List<DepartmentDetailDTO> departmentDetailDTOList);
    List<DepartmentDetailDTO> getDTOList(List<DepartmentDetail> departmentDetailList);

    // CRUD Operations
    void createDepartmentDetail(DepartmentDetailDTO departmentDetailDTO);
    void updateDepartmentDetail(Long departmentToUpdateId, DepartmentDetailDTO newDepartmentDetailDTO);
    DepartmentDetail getDepartmentDetailById(Long departmentDetailToGetId);
    void deleteDepartmentDetail(DepartmentDetailDTO departmentDetailToDeleteDTO);
    void deleteDepartmentDetailById(Long departmentDetailToDeleteId);

    // Department Detail Repository
    List<Long> getUserIdsInTheDepartmentWithId(Long departmentId);
    List<DepartmentDetail> getDepartmentDetailsByUserId(Long userId);
    List<DepartmentDetail> getDepartmentDetailsByDepartmentId(Long deparmentId);
}
