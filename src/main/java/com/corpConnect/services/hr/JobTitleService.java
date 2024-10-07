package com.corpConnect.services.hr;

import com.corpConnect.dtos.hr.JobTitleDTO;
import com.corpConnect.entities.hr.JobTitle;

import java.util.List;

public interface JobTitleService {

    JobTitle getEntity(JobTitleDTO jobTitleDTO);
    JobTitleDTO getDTO(JobTitle jobTitle);
    List<JobTitle> getEntityList(List<JobTitleDTO> jobTitleDTOList);
    List<JobTitleDTO> getDTOList(List<JobTitle> jobTitleList);

    void createJobTitles(JobTitleDTO jobTitleDTO);
    void updateJobTitles(Long oldJobTitleId, JobTitleDTO jobTitleDTO);
    void deleteJobTitles(JobTitleDTO jobTitleDTO);
    void deleteJobTitlesById(Long jobTitlesId, Boolean isPermanentDelete);

    List<JobTitle> getAllJobTitles();
    List<JobTitle> getAllNonDeletedJobTitles();
    List<JobTitle> getAllDeletedJobTitles();
    List<JobTitle> getJobTitlesById(Long jobTitlesId);
    List<JobTitle> getJobTitlesByName(String jobTitlesName);
    List<JobTitle> getJobTitlesByGrade(String grade);
}
