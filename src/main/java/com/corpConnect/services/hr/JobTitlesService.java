package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.JobTitlesDTO;
import com.corpConnect.entities.hr.JobTitles;

import java.util.List;

public interface JobTitlesService {

    JobTitles getEntity(JobTitlesDTO jobTitlesDTO);
    JobTitlesDTO getDTO(JobTitles jobTitles);
    List<JobTitles> getEntityList(List<JobTitlesDTO> jobTitlesDTOList);
    List<JobTitlesDTO> getDTOList(List<JobTitles> jobTitlesList);

    void createJobTitles(JobTitlesDTO jobTitlesDTO);
    void updateJobTitles(Long oldJobTitleId, JobTitlesDTO jobTitlesDTO);
    void deleteJobTitles(JobTitlesDTO jobTitlesDTO);
    void deleteJobTitlesById(Long jobTitlesId, Boolean isPermanentDelete);

    List<JobTitles> getAllJobTitles();
    List<JobTitles> getAllNonDeletedJobTitles();
    List<JobTitles> getAllDeletedJobTitles();
    List<JobTitles> getJobTitlesById(Long jobTitlesId);
    List<JobTitles> getJobTitlesByName(String jobTitlesName);
    List<JobTitles> getJobTitlesByGrade(String grade);
}