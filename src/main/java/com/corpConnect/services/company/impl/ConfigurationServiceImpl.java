package com.corpConnect.services.company.impl;

import com.corpConnect.dtos.company.ConfigurationDTO;
import com.corpConnect.entities.company.Configuration;
import com.corpConnect.exceptions.hr.HolidayRelatedException;
import com.corpConnect.exceptions.hr.JobTitleRelatedException;
import com.corpConnect.mappers.company.ConfigurationMapper;
import com.corpConnect.repositories.company.ConfigurationRepository;
import com.corpConnect.services.company.ConfigurationService;
import com.corpConnect.utils.constants.LogConstants;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigurationServiceImpl implements ConfigurationService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

    private final ConfigurationMapper configurationMapper;
    private final ConfigurationRepository configurationRepository;

    @Override
    public Configuration getEntity(ConfigurationDTO configurationDTO) {
        return configurationMapper.toEntity(configurationDTO);
    }

    @Override
    public ConfigurationDTO getDTO(Configuration configuration) {
        return configurationMapper.toDTO(configuration);
    }

    @Override
    public List<Configuration> getEntityList(List<ConfigurationDTO> configurationDTOList) {
        return configurationMapper.toEntityList(configurationDTOList);
    }

    @Override
    public List<ConfigurationDTO> getDTOList(List<Configuration> configurationList) {
        return configurationMapper.toDTOList(configurationList);
    }

    @Override
    public void createConfiguration(ConfigurationDTO configurationDTO) {
        try {
            configurationRepository.save(this.getEntity(configurationDTO));
            logger.info(LogConstants.getCreatedSuccessfullyMessage("Configuration", "DTO", configurationDTO, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Configuration", "Name", configurationDTO.getName(), "while creating"));
            throw new RuntimeException(MessageConstants.CompanyConfiguration.CONFIGURATION_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateConfiguration(Long oldConfigurationId, ConfigurationDTO configurationDTO) {
        Configuration oldConfiguration = configurationRepository.findById(oldConfigurationId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Configuration", "update", "ID", oldConfigurationId, null));
                    return new JobTitleRelatedException(MessageConstants.CompanyConfiguration.CONFIGURATION_NOT_FOUND);
                });

        try {
            configurationMapper.updateEntityFromDTO(configurationDTO, oldConfiguration);
            configurationRepository.save(oldConfiguration);
            logger.info(LogConstants.getUpdatedSuccessfullyMessage("Configuration", "DTO", configurationDTO, "ID", oldConfigurationId, null));
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getAlreadyExistsMessage("Configuration", "Name", configurationDTO.getName(), "while updating"));
            throw new RuntimeException(MessageConstants.CompanyConfiguration.CONFIGURATION_CREATED);
        }
    }

    @Override
    public void deleteConfiguration(ConfigurationDTO configurationDTO, Boolean isPermanentDelete) {
        Configuration configurationToDelete = this.getEntity(configurationDTO);

        try {
            if (isPermanentDelete) {
                configurationRepository.delete(configurationToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Configuration", "Permanent", "DTO", configurationDTO, null));
            } else {
                configurationToDelete.setDeleted(true);
                configurationRepository.save(configurationToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Configuration", "Soft", "DTO", configurationDTO, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Configuration", "DTO", configurationDTO, null));
            throw new RuntimeException(MessageConstants.CompanyConfiguration.DataIntegrityViolation);
        }
    }

    @Override
    public void deleteConfigurationById(Long configurationId, Boolean isPermanentDelete) {
        try {
            Configuration configurationToDelete = configurationRepository.findById(configurationId).orElseThrow(
                    () -> {
                        logger.error(LogConstants.getNotFoundMessage("Configuration", "delete", "ID", configurationId, null));
                        return new JobTitleRelatedException(MessageConstants.CompanyConfiguration.CONFIGURATION_NOT_FOUND);
                    });

            if (isPermanentDelete) {
                configurationRepository.delete(configurationToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Configuration", "Permanent", "ID", configurationId, null));
            } else {
                configurationToDelete.setDeleted(true);
                configurationRepository.save(configurationToDelete);
                logger.info(LogConstants.getDeletedSuccessfullyMessage("Configuration", "Soft", "ID", configurationId, null));
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(LogConstants.getIsUsedSomewhereMessage("Configuration", "ID", configurationId, null));
            throw new RuntimeException(MessageConstants.CompanyConfiguration.DataIntegrityViolation);
        }
    }

    @Override
    public List<Configuration> getAllConfigurations(Boolean withDeletedCheck) {
        if (withDeletedCheck == null) {
            logger.info(LogConstants.getFoundAllMessage("Configuration", "get", "without deleted check"));
            return configurationRepository.findAll();
        } else if (withDeletedCheck) {
            return this.getAllDeletedConfigurations();
        } else {
            return this.getAllNonDeletedConfigurations();
        }
    }

    @Override
    public List<Configuration> getAllNonDeletedConfigurations() {
        logger.info(LogConstants.getFoundAllMessage("Configuration", "get", "deleted check-" + false));
        return configurationRepository.findByDeleted(false);
    }

    @Override
    public List<Configuration> getAllDeletedConfigurations() {
        logger.info(LogConstants.getFoundAllMessage("Configuration", "get", "deleted check-" + true));
        return configurationRepository.findByDeleted(true);
    }

    @Override
    public List<Configuration> getConfigurationById(Long configurationId) {
        return Collections.singletonList(configurationRepository.findById(configurationId).orElseThrow(
                () -> {
                    logger.error(LogConstants.getNotFoundMessage("Configuration", "get", "ID", configurationId, null));
                    return new HolidayRelatedException(MessageConstants.CompanyConfiguration.CONFIGURATION_NOT_FOUND);
                }
        ));
    }

    @Override
    public Configuration getNonDeletedConfigurationByName(String configurationName) {
        return configurationRepository.findByNameIgnoreCaseAndDeleted(configurationName, false);
    }
}
