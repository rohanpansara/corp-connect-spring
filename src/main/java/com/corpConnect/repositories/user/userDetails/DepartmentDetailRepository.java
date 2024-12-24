package com.corpConnect.repositories.user.userDetails;

import com.corpConnect.entities.user.userDetails.DepartmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDetailRepository extends JpaRepository<DepartmentDetail, Long> {

    List<DepartmentDetail> findByUserId(Long userId);
    List<DepartmentDetail> findByUserIdIn(List<Long> userIdList);
    List<DepartmentDetail> findByDepartmentId(Long departmentId);
    List<DepartmentDetail> findByDepartmentIdIn(List<Long> departmentIdList);

    List<Long> findUserIdByDepartmentId(Long departmentId);

}
