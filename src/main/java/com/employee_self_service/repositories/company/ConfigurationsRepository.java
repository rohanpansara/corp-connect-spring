package com.employee_self_service.repositories.company;

import com.employee_self_service.entities.company.Configurations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationsRepository extends JpaRepository<Configurations, Long> {

    Configurations findByName(String name);
    String getMaxValueByName(String name);
    String getMinValueByName(String name);

    List<Configurations> findByIsEnabled(boolean isEnabled);
    List<Configurations> findByIsDeleted(boolean isDeleted);

    boolean existsEnabledByName(String name);
    boolean existsDeletedByName(String name);
}

