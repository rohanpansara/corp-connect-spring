package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.JobTitleDTO;
import com.corpConnect.entities.hr.JobTitle;
import com.corpConnect.exceptions.hr.HolidayNotFoundException;
import com.corpConnect.exceptions.hr.JobTitleNotFoundException;
import com.corpConnect.mappers.hr.JobTitleMapper;
import com.corpConnect.repositories.hr.JobTitleRepository;
import com.corpConnect.services.hr.JobTitleService;
import com.corpConnect.utils.constants.MessageConstants;
import com.corpConnect.utils.constants.LogConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTitleServiceImpl implements JobTitleService {

    private static final Logger logger = LoggerFactory.getLogger(JobTitleServiceImpl.class);

    private final JobTitleMapper jobTitleMapper;
    private final JobTitleRepository jobTitleRepository;

    @Override
    public JobTitle getEntity(JobTitleDTO jobTitleDTO) {
        return jobTitleMapper.toEntity(jobTitleDTO);
    }

    @Override
    public JobTitleDTO getDTO(JobTitle jobTitle) {
        return jobTitleMapper.toDTO(jobTitle);
    }

    @Override
    public List<JobTitle> getEntityList(List<JobTitleDTO> jobTitleDTOList) {
        return jobTitleMapper.toEntityList(jobTitleDTOList);
    }

    @Override
    public List<JobTitleDTO> getDTOList(List<JobTitle> jobTitleList) {
        return jobTitleMapper.toDTOList(jobTitleList);
    }

    @Override
    public void createJobTitles(JobTitleDTO jobTitleDTO) {
        try {
            jobTitleRepository.save(this.getEntity(jobTitleDTO));
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Job Title", "DTO", jobTitleDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Job Title", "Name", jobTitleDTO.getName(), "while creating"));
            throw new RuntimeException(MessageConstants.JobTitle.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateJobTitles(Long oldJobTitleId, JobTitleDTO jobTitleDTO) {
        JobTitle oldJobTitle = jobTitleRepository.findById(oldJobTitleId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Job Title", "update", "ID", oldJobTitleId, null));
                    return new JobTitleNotFoundException(MessageConstants.JobTitle.JOB_TITLE_NOT_FOUND);
                });

        try {
            jobTitleMapper.updateEntityFromDTO(jobTitleDTO, oldJobTitle);
            jobTitleRepository.save(oldJobTitle);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Job Title", "DTO", jobTitleDTO, "ID", oldJobTitleId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Job Title", "Name", jobTitleDTO.getName(), "while updating"));
            throw new RuntimeException(MessageConstants.JobTitle.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteJobTitles(JobTitleDTO jobTitleDTO, Boolean isPermanentDelete) {
        JobTitle jobTitleToDelete = this.getEntity(jobTitleDTO);

        try {
            if (isPermanentDelete) {
                jobTitleRepository.delete(jobTitleToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Job Title", "Permanent", "DTO", jobTitleDTO, null));
            } else {
                jobTitleToDelete.setDeleted(true);
                jobTitleRepository.save(jobTitleToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Job Title", "Soft", "DTO", jobTitleDTO, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Job Title", "DTO", jobTitleDTO, null));
            throw new RuntimeException(MessageConstants.JobTitle.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteJobTitlesById(Long jobTitlesId, Boolean isPermanentDelete) {
        try {
            JobTitle jobTitleToDelete = jobTitleRepository.findById(jobTitlesId).orElseThrow(
                    () -> {
                        logger.error(LogConstants.getNotFoundMessage("Job Title", "delete", "ID", jobTitlesId, null));
                        return new JobTitleNotFoundException(MessageConstants.JobTitle.JOB_TITLE_NOT_FOUND);
                    });

            if (isPermanentDelete) {
                jobTitleRepository.delete(jobTitleToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Job Title", "Permanent", "ID", jobTitlesId, null));
            } else {
                jobTitleToDelete.setDeleted(true);
                jobTitleRepository.save(jobTitleToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Job Title", "Soft", "ID", jobTitlesId, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Job Title", "ID", jobTitlesId, null));
            throw new RuntimeException(MessageConstants.JobTitle.DataIntegrityViolation);
        }
    }

    @Override
    public List<JobTitle> getAllJobTitles() {
        return jobTitleRepository.findAll();
    }

    @Override
    public List<JobTitle> getAllNonDeletedJobTitles() {
        return jobTitleRepository.findByIsDeleted(false);
    }

    @Override
    public List<JobTitle> getAllDeletedJobTitles() {
        return jobTitleRepository.findByIsDeleted(true);
    }

    @Override
    public List<JobTitle> getJobTitlesById(Long jobTitleId) {
        return Collections.singletonList(jobTitleRepository.findById(jobTitleId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Job Title", "get", "ID", jobTitleId, null));
                    return new HolidayNotFoundException(MessageConstants.JobTitle.JOB_TITLE_NOT_FOUND);
                }
        ));
    }

    @Override
    public List<JobTitle> getJobTitlesByName(String name) {
        return jobTitleRepository.findByNameContaining(name);
    }

    @Override
    public List<JobTitle> getJobTitlesByGrade(String grade) {
        return jobTitleRepository.findByGrade(grade);
    }
}
