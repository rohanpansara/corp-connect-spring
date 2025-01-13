package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

    List<JobTitle> findByGrade(String grade);
    List<JobTitle> findByDeleted(boolean isDeleted);
    List<JobTitle> findByNameContaining(String jobTitleName);

}
