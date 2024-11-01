package com.corpConnect.services.company;

import com.corpConnect.dtos.company.ConfigurationDTO;
import com.corpConnect.entities.company.Configuration;

import java.util.List;

public interface ConfigurationService {
    Configuration getEntity(ConfigurationDTO configurationDTO);
    ConfigurationDTO getDTO(Configuration configuration);
    List<Configuration> getEntityList(List<ConfigurationDTO> configurationDTOList);
    List<ConfigurationDTO> getDTOList(List<Configuration> configurationList);

    void createConfiguration(ConfigurationDTO configurationDTO);
    void updateConfiguration(Long oldConfigurationId, ConfigurationDTO configurationDTO);
    void deleteConfiguration(ConfigurationDTO configurationDTO, Boolean isPermanentDelete);
    void deleteConfigurationById(Long configurationId, Boolean isPermanentDelete);

    List<Configuration> getAllConfigurations(Boolean withDeletedCheck);
    List<Configuration> getAllNonDeletedConfigurations();
    List<Configuration> getAllDeletedConfigurations();
    List<Configuration> getConfigurationById(Long configurationId);
}
