package com.corpConnect.repositories.company;

import com.corpConnect.entities.company.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

    EmailTemplate findByName(String name);

}
