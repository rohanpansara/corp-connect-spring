package com.corpConnect.services.hr.impl;

import com.corpConnect.dtos.hr.JobTitleDTO;
import com.corpConnect.entities.hr.JobTitle;
import com.corpConnect.exceptions.hr.JobTitleNotFoundException;
import com.corpConnect.mappers.hr.JobTitlesMapper;
import com.corpConnect.repositories.hr.JobTitleRepository;
import com.corpConnect.services.hr.JobTitleService;
import com.corpConnect.utils.constants.CorpConnectConstants;
import com.corpConnect.utils.constants.LogConstants;
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
public class JobTitleServiceImpl implements JobTitleService {

    private static final Logger logger = LoggerFactory.getLogger(JobTitleServiceImpl.class);

    private final JobTitlesMapper jobTitlesMapper;
    private final JobTitleRepository jobTitleRepository;

    @Override
    public JobTitle getEntity(JobTitleDTO jobTitleDTO) {
        return jobTitlesMapper.toEntity(jobTitleDTO);
    }

    @Override
    public JobTitleDTO getDTO(JobTitle jobTitle) {
        return jobTitlesMapper.toDTO(jobTitle);
    }

    @Override
    public List<JobTitle> getEntityList(List<JobTitleDTO> jobTitleDTOList) {
        return jobTitlesMapper.toEntityList(jobTitleDTOList);
    }

    @Override
    public List<JobTitleDTO> getDTOList(List<JobTitle> jobTitleList) {
        return jobTitlesMapper.toDTOList(jobTitleList);
    }

    @Override
    public void createJobTitles(JobTitleDTO jobTitleDTO) {
        try {
            jobTitleRepository.save(this.getEntity(jobTitleDTO));
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Job Title", "DTO", jobTitleDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsWhileCreatingMessage("Job Title", "Name", jobTitleDTO.getName(), "while creating"));
            throw new RuntimeException(CorpConnectConstants.JobTitles.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateJobTitles(Long oldJobTitleId, JobTitleDTO jobTitleDTO) {
        JobTitle oldJobTitle = jobTitleRepository.findById(oldJobTitleId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Job Title", "update", "ID", oldJobTitleId, null));
                    return new JobTitleNotFoundException(CorpConnectConstants.JobTitles.JOB_TITLE_NOT_FOUND);
                });

        try {
            jobTitlesMapper.updateEntityFromDTO(jobTitleDTO, oldJobTitle);
            jobTitleRepository.save(oldJobTitle);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Job Title", "DTO", jobTitleDTO, "ID", oldJobTitleId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsWhileCreatingMessage("Job Title", "Name", jobTitleDTO.getName(), "while updating"));
            throw new RuntimeException(CorpConnectConstants.JobTitles.JOB_TITLE_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteJobTitles(JobTitleDTO jobTitleDTO) {
        try {
            jobTitleRepository.delete(this.getEntity(jobTitleDTO));
            logger.info(LogConstants.getDeletedSuccessfullyMessage("Job Title", "Permanent", "DTO", jobTitleDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Job Title", "DTO", jobTitleDTO, null));
            throw new RuntimeException(CorpConnectConstants.JobTitles.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteJobTitlesById(Long jobTitlesId, Boolean isPermanentDelete) {
        try {
            JobTitle jobTitleToDelete = jobTitleRepository.findById(jobTitlesId).orElseThrow(
                    () -> {
                        logger.error(LogConstants.getNotFoundMessage("Job Title", "delete", "ID", jobTitlesId, null));
                        return new JobTitleNotFoundException(CorpConnectConstants.JobTitles.JOB_TITLE_NOT_FOUND);
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
            throw new RuntimeException(CorpConnectConstants.JobTitles.DataIntegrityViolation);
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
    public List<JobTitle> getJobTitlesById(Long jobTitlesId) {
        Optional<JobTitle> jobTitlesOptional = jobTitleRepository.findById(jobTitlesId);
        return jobTitlesOptional.map(Collections::singletonList).orElseGet(List::of);
    }

    @Override
    public List<JobTitle> getJobTitlesByName(String jobTitlesName) {
        return jobTitleRepository.findByNameContaining(jobTitlesName);
    }

    @Override
    public List<JobTitle> getJobTitlesByGrade(String grade) {
        return jobTitleRepository.findByGrade(grade);
    }
}
