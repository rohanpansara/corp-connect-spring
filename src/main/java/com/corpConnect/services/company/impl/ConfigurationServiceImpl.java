package com.corpConnect.services.company.impl;

import com.corpConnect.dtos.company.ConfigurationDTO;
import com.corpConnect.entities.company.Configuration;
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

    }

    @Override
    public void deleteConfiguration(ConfigurationDTO configurationDTO, Boolean isPermanentDelete) {

    }

    @Override
    public void deleteConfigurationById(Long configurationId, Boolean isPermanentDelete) {

    }

    @Override
    public List<Configuration> getAllConfigurations(Boolean withDeletedCheck) {
        return List.of();
    }

    @Override
    public List<Configuration> getAllNonDeletedConfigurations() {
        return List.of();
    }

    @Override
    public List<Configuration> getAllDeletedConfigurations() {
        return List.of();
    }

    @Override
    public List<Configuration> getConfigurationById(Long configurationId) {
        return List.of();
    }
}
