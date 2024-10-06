package com.corpConnect.repositories.hr;

import com.corpConnect.entities.hr.JobTitles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobTitlesRepository extends JpaRepository<JobTitles, Long> {

    List<JobTitles> findByGrade(String grade);
    List<JobTitles> findByIsDeleted(boolean isDeleted);
    List<JobTitles> findByNameContaining(String jobTitleName);

    boolean existsDeletedByName(String name);

}
