package com.corpConnect.controllers.company;

import com.corpConnect.dtos.common.ResponseDTO;
import com.corpConnect.dtos.company.ConfigurationDTO;
import com.corpConnect.services.company.ConfigurationService;
import com.corpConnect.utils.constants.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company/configuration")
@PreAuthorize("hasRole('ADMIN')")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<ResponseDTO<Void>> createConfiguration(@RequestBody ConfigurationDTO configurationDTO){
        configurationService.createConfiguration(configurationDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.CompanyConfiguration.CONFIGURATION_CREATED));
    }

    @PutMapping(value = "/{configuration-id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ResponseDTO<Void>> updateConfiguration(@PathVariable("configuration-id") Long oldConfigurationId, @RequestBody ConfigurationDTO configurationDTO) {
        configurationService.updateConfiguration(oldConfigurationId, configurationDTO);
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.CompanyConfiguration.CONFIGURATION_UPDATED));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseDTO<List<ConfigurationDTO>>> fetchAllConfigurations(@RequestParam(required = false, value = "deleted") Boolean isDeleted) {
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.CompanyConfiguration.CONFIGURATION_LIST_FOUND, configurationService.getDTOList(configurationService.getAllConfigurations(isDeleted))));
    }

    @GetMapping(value = "/{configuration-id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ResponseDTO<List<ConfigurationDTO>>> fetchConfigurationByConfigurationId(@PathVariable("configuration-id") Long configurationId){
        return ResponseEntity.ok(ResponseDTO.success(MessageConstants.CompanyConfiguration.CONFIGURATION_FOUND, configurationService.getDTOList(configurationService.getConfigurationById(configurationId))));
    }
}
