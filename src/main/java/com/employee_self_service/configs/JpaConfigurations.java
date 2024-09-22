package com.employee_self_service.configs;

import com.employee_self_service.audits.ApplicationAuditAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "applicationAuditAware")
public class JpaConfigurations {

    @Bean
    public ApplicationAuditAware applicationAuditAware() {
        return new ApplicationAuditAware();
    }
}

