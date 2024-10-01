package com.employee_self_service.services.hr.impl;

import com.employee_self_service.dtos.hr.JobTitlesDTO;
import com.employee_self_service.entities.hr.JobTitles;
import com.employee_self_service.exceptions.hr.JobTitleNotFoundException;
import com.employee_self_service.mappers.hr.JobTitlesMapper;
import com.employee_self_service.repositories.hr.JobTitlesRepository;
import com.employee_self_service.services.hr.JobTitlesService;
import com.employee_self_service.utils.EssConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobTitlesServiceImpl implements JobTitlesService {

    private final JobTitlesMapper jobTitlesMapper;
    private final JobTitlesRepository jobTitlesRepository;

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
        try {
            jobTitlesRepository.save(this.getEntity(jobTitlesDTO));
        } catch (Exception e) {
            throw new RuntimeException(EssConstants.JobTitles.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateJobTitles(Long oldJobTitleId, JobTitlesDTO jobTitlesDTO) {
        JobTitles oldJobTitle = jobTitlesRepository.findById(oldJobTitleId).orElseThrow(
                () -> new JobTitleNotFoundException(EssConstants.JobTitles.JOB_TITLE_NOT_FOUND));

        try {
            jobTitlesMapper.updateEntityFromDTO(jobTitlesDTO, oldJobTitle);
            jobTitlesRepository.save(oldJobTitle);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(EssConstants.JobTitles.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteJobTitles(JobTitlesDTO jobTitlesDTO) {
        try {
            jobTitlesRepository.delete(this.getEntity(jobTitlesDTO));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(EssConstants.JobTitles.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteJobTitlesById(Long jobTitlesId, Boolean isPermanentDelete) {
        try {
            JobTitles jobTitleToDelete = jobTitlesRepository.findById(jobTitlesId).orElseThrow(
                    () -> new JobTitleNotFoundException(EssConstants.JobTitles.JOB_TITLE_NOT_FOUND));

            if(isPermanentDelete){
                jobTitlesRepository.delete(jobTitleToDelete);
            } else {
                jobTitleToDelete.setDeleted(true);
                jobTitlesRepository.save(jobTitleToDelete);
            }
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(EssConstants.JobTitles.DataIntegrityViolation);
        }
    }

    @Override
    public List<JobTitles> getAllJobTitles() {
        return jobTitlesRepository.findAll();
    }

    @Override
    public List<JobTitles> getJobTitlesById(Long jobTitlesId) {
        Optional<JobTitles> jobTitlesOptional = jobTitlesRepository.findById(jobTitlesId);
        if(jobTitlesOptional.isEmpty()){
            throw new JobTitleNotFoundException(EssConstants.JobTitles.JOB_TITLE_NOT_FOUND);
        }
        return Collections.singletonList(jobTitlesOptional.get());
    }

    @Override
    public List<JobTitles> getJobTitlesByName(String jobTitlesName) {
        return jobTitlesRepository.findByNameContaining(jobTitlesName);
    }

    @Override
    public List<JobTitles> getJobTitlesByGrade(String grade) {
        return jobTitlesRepository.findByGrade(grade);
    }
}
