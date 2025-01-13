CREATE TABLE IF NOT EXISTS CorpConnect.AUTOMATION_EMAIL (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from BaseEntity
    name VARCHAR(150) NOT NULL, -- Inherited from NameWithDeleteEntity
    time TIME, -- Specific to Email, time for automation
    zoneId_region VARCHAR(100), -- Specific to Email, time zone region
    email_template_id BIGINT, -- Specific to Email, stores email template
    send_on_specificDate DATE DEFAULT NULL, -- Specific to Email, stores specific date to send email on
    send_daily BOOLEAN NOT NULL DEFAULT false, -- Specific to Email, flag for daily sending
    send_monthly BOOLEAN NOT NULL DEFAULT false, -- Specific to Email, flag for monthly sending
    send_yearly BOOLEAN NOT NULL DEFAULT false, -- Specific to Email, flag for yearly sending
    send_during_holiday BOOLEAN NOT NULL DEFAULT false, -- Specific to Email, flag for holiday sending
    deleted BOOLEAN NOT NULL DEFAULT false, -- Inherited from NameWithDeleteEntity, for logical deletion
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    FOREIGN KEY (email_template_id) REFERENCES CorpConnect.COMPANY_EMAIL_TEMPLATES(id) ON DELETE SET NULL -- Foreign key constraint
);
