package com.corpConnect.repositories.company;

import com.corpConnect.entities.company.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

    Configuration findByNameIgnoreCaseAndDeleted(String name, boolean isDeleted);
    String getMaxValueByName(String name);
    String getMinValueByName(String name);

    List<Configuration> findByEnabled(boolean isEnabled);
    List<Configuration> findByDeleted(boolean isDeleted);

    boolean existsEnabledByName(String name);
    boolean existsDeletedByName(String name);

}

