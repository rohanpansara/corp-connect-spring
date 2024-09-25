package com.employee_self_service.services.hr.impl;

import com.employee_self_service.dtos.hr.JobTitlesDTO;
import com.employee_self_service.entities.hr.JobTitles;
import com.employee_self_service.mappers.hr.JobTitlesMapper;
import com.employee_self_service.repositories.hr.JobTitlesRepository;
import com.employee_self_service.services.hr.JobTitlesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitlesServiceImpl implements JobTitlesService {

    private final JobTitlesMapper jobTitlesMapper;
    private final JobTitlesRepository jobTitlesRepository;

    public JobTitlesServiceImpl(JobTitlesMapper jobTitlesMapper, JobTitlesRepository jobTitlesRepository) {
        this.jobTitlesMapper = jobTitlesMapper;
        this.jobTitlesRepository = jobTitlesRepository;
    }

    @Override
    public JobTitles getEntity(JobTitlesDTO jobTitlesDTO) {
        return jobTitlesMapper.toEntity(jobTitlesDTO);
    }

    @Override
    public JobTitlesDTO getDTO(JobTitles jobTitles) {
        return jobTitlesMapper.toDTO(jobTitles);
    }

    @Override
    public List<JobTitles> getEntityList(List<JobTitlesDTO> jobTitlesDTOList) {
        return jobTitlesMapper.toEntityList(jobTitlesDTOList);
    }

    @Override
    public List<JobTitlesDTO> getDTOList(List<JobTitles> jobTitlesList) {
        return jobTitlesMapper.toDTOList(jobTitlesList);
    }

    @Override
    public void createJobTitles(JobTitlesDTO jobTitlesDTO) {
        jobTitlesRepository.save(this.getEntity(jobTitlesDTO));
    }

    @Override
    public void updateJobTitles(JobTitlesDTO jobTitlesDTO) {

    }

    @Override
    public void deleteJobTitles(JobTitlesDTO jobTitlesDTO) {

    }

    @Override
    public void deleteJobTitlesById(Long jobTitlesId) {

    }

    @Override
    public List<JobTitles> getAllJobTitles() {
        return List.of();
    }

    @Override
    public List<JobTitles> getJobTitlesById(Long jobTitlesId) {
        return List.of();
    }

    @Override
    public List<JobTitles> getJobTitlesByName(String jobTitlesName) {
        return List.of();
    }

    @Override
    public List<JobTitles> getJobTitlesByGrade(String jobTitlesName) {
        return List.of();
    }
}
