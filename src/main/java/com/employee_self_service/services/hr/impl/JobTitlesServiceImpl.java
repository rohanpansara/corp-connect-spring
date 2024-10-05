package com.employee_self_service.services.hr.impl;

import com.employee_self_service.dtos.hr.JobTitlesDTO;
import com.employee_self_service.entities.hr.JobTitles;
import com.employee_self_service.exceptions.hr.JobTitleNotFoundException;
import com.employee_self_service.mappers.hr.JobTitlesMapper;
import com.employee_self_service.repositories.hr.JobTitlesRepository;
import com.employee_self_service.services.hr.JobTitlesService;
import com.employee_self_service.utils.constants.EssConstants;
import com.employee_self_service.utils.constants.LogConstants;
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
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Job Title", "DTO", jobTitlesDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsWhileCreatingMessage("Job Title", jobTitlesDTO.getName(), null));
            throw new RuntimeException(EssConstants.JobTitles.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateJobTitles(Long oldJobTitleId, JobTitlesDTO jobTitlesDTO) {
        JobTitles oldJobTitle = jobTitlesRepository.findById(oldJobTitleId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Job Title", "ID", oldJobTitleId, "while updating"));
                    return new JobTitleNotFoundException(EssConstants.JobTitles.JOB_TITLE_NOT_FOUND);
                });

        try {
            jobTitlesMapper.updateEntityFromDTO(jobTitlesDTO, oldJobTitle);
            jobTitlesRepository.save(oldJobTitle);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Job Title", "DTO", jobTitlesDTO, "ID", oldJobTitleId, "while updating"));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsWhileCreatingMessage("Job Title", jobTitlesDTO.getName(), "while updating"));
            throw new RuntimeException(EssConstants.JobTitles.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteJobTitles(JobTitlesDTO jobTitlesDTO) {
        try {
            jobTitlesRepository.delete(this.getEntity(jobTitlesDTO));
            logger.info(LogConstants.getDeletedSuccessfullyMessage("Job Title", "Permanent", "DTO", jobTitlesDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Job Title", "DTO", jobTitlesDTO, null));
            throw new RuntimeException(EssConstants.JobTitles.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteJobTitlesById(Long jobTitlesId, Boolean isPermanentDelete) {
        try {
            JobTitles jobTitleToDelete = jobTitlesRepository.findById(jobTitlesId).orElseThrow(
                    () -> {
                        logger.error(LogConstants.getNotFoundMessage("Job Title", "ID", jobTitlesId, "while deleting"));
                        return new JobTitleNotFoundException(EssConstants.JobTitles.JOB_TITLE_NOT_FOUND);
                    });

            if (isPermanentDelete) {
                jobTitlesRepository.delete(jobTitleToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Job Title", "Permanent", "ID", jobTitlesId, null));
            } else {
                jobTitleToDelete.setDeleted(true);
                jobTitlesRepository.save(jobTitleToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Job Title", "Soft", "ID", jobTitlesId, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Job Title", "ID", jobTitlesId, null));
            throw new RuntimeException(EssConstants.JobTitles.DataIntegrityViolation);
        }
    }

    @Override
    public List<JobTitles> getAllJobTitles() {
        return jobTitlesRepository.findAll();
    }

    @Override
    public List<JobTitles> getAllNonDeletedJobTitles() {
        return jobTitlesRepository.findByIsDeleted(false);
    }

    @Override
    public List<JobTitles> getAllDeletedJobTitles() {
        return jobTitlesRepository.findByIsDeleted(true);
    }

    @Override
    public List<JobTitles> getJobTitlesById(Long jobTitlesId) {
        Optional<JobTitles> jobTitlesOptional = jobTitlesRepository.findById(jobTitlesId);
        return jobTitlesOptional.map(Collections::singletonList).orElseGet(List::of);
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
