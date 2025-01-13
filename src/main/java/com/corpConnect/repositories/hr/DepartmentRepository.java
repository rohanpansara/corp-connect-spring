package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByDeleted(boolean isDeleted);
    List<Department> findByNameContaining(String name);

}
