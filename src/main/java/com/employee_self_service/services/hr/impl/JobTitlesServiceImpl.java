package com.employee_self_service.services.hr.impl;

import com.employee_self_service.dtos.hr.JobTitlesDTO;
import com.employee_self_service.entities.hr.JobTitles;
import com.employee_self_service.exceptions.hr.JobTitleNotFoundException;
import com.employee_self_service.mappers.hr.JobTitlesMapper;
import com.employee_self_service.repositories.hr.JobTitlesRepository;
import com.employee_self_service.services.hr.JobTitlesService;
import com.employee_self_service.utils.EssConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobTitlesServiceImpl implements JobTitlesService {

    private static final Logger logger = LoggerFactory.getLogger(JobTitlesServiceImpl.class);

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
            logger.info("Created: Attempt to delete a job title with dto: {}", jobTitlesDTO);
        } catch (DataIntegrityViolationException e) {
            logger.error("Already Exists: Attempt to create a job title with dto: {}", jobTitlesDTO);
            throw new RuntimeException(EssConstants.JobTitles.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateJobTitles(Long oldJobTitleId, JobTitlesDTO jobTitlesDTO) {
        JobTitles oldJobTitle = jobTitlesRepository.findById(oldJobTitleId).orElseThrow(
                () -> {
                    logger.error("Not Found: Attempt to update a job title with id: {}", oldJobTitleId);
                    return new JobTitleNotFoundException(EssConstants.JobTitles.JOB_TITLE_NOT_FOUND);
                });

        try {
            jobTitlesMapper.updateEntityFromDTO(jobTitlesDTO, oldJobTitle);
            jobTitlesRepository.save(oldJobTitle);
            logger.info("Updated: Attempt to update a job title with dto: {}", jobTitlesDTO);
        } catch (DataIntegrityViolationException e) {
            logger.error("Already Exists: Attempt to update a job title with name: {}", jobTitlesDTO.getName());
            throw new RuntimeException(EssConstants.JobTitles.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteJobTitles(JobTitlesDTO jobTitlesDTO) {
        try {
            jobTitlesRepository.delete(this.getEntity(jobTitlesDTO));
            logger.info("Permanent Delete: Attempt to delete a job title with dto: {}", jobTitlesDTO);
        } catch (DataIntegrityViolationException e) {
            logger.error("In Use: Attempt to delete a job title with DTO: {}", jobTitlesDTO);
            throw new RuntimeException(EssConstants.JobTitles.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteJobTitlesById(Long jobTitlesId, Boolean isPermanentDelete) {
        try {
            JobTitles jobTitleToDelete = jobTitlesRepository.findById(jobTitlesId).orElseThrow(
                    () -> {
                        logger.error("Not Found: Attempt to delete a job title with id: {}", jobTitlesId);
                        return new JobTitleNotFoundException(EssConstants.JobTitles.JOB_TITLE_NOT_FOUND);
                    });

            if(isPermanentDelete){
                jobTitlesRepository.delete(jobTitleToDelete);
                logger.info("Soft Delete: Attempt to delete a job title with id: {}", jobTitlesId);
            } else {
                jobTitleToDelete.setDeleted(true);
                jobTitlesRepository.save(jobTitleToDelete);
                logger.info("Permanent Delete: Attempt to delete a job title with id: {}", jobTitlesId);
            }
        } catch (DataIntegrityViolationException e) {
            logger.error("In Use: Attempt to delete a job title with id: {}", jobTitlesId);
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
            logger.error("Not Found: Attempt to get a job title with id: {}", jobTitlesId);
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
