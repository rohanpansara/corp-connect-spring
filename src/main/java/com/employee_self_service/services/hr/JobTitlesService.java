package com.employee_self_service.services.hr;

import com.employee_self_service.dtos.hr.JobTitlesDTO;
import com.employee_self_service.entities.hr.JobTitles;

import java.util.List;

public interface JobTitlesService {

    JobTitles getEntity(JobTitlesDTO jobTitlesDTO);
    JobTitlesDTO getDTO(JobTitles jobTitles);
    List<JobTitles> getEntityList(List<JobTitlesDTO> jobTitlesDTOList);
    List<JobTitlesDTO> getDTOList(List<JobTitles> jobTitlesList);

    void createJobTitles(JobTitlesDTO jobTitlesDTO);
    void updateJobTitles(Long oldJobTitleId, JobTitlesDTO jobTitlesDTO);
    void deleteJobTitles(JobTitlesDTO jobTitlesDTO);
    void deleteJobTitlesById(Long jobTitlesId);

    List<JobTitles> getAllJobTitles();
    List<JobTitles> getJobTitlesById(Long jobTitlesId);
    List<JobTitles> getJobTitlesByName(String jobTitlesName);
    List<JobTitles> getJobTitlesByGrade(String grade);
}
