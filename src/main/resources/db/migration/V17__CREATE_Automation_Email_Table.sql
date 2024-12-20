CREATE TABLE IF NOT EXISTS CorpConnect.AUTOMATION_EMAIL (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from BaseEntity
    name VARCHAR(150) NOT NULL, -- Inherited from NameWithDeleteEntity
    time TIME, -- Specific to Email, time for automation
    zoneIdRegion VARCHAR(100), -- Specific to Email, time zone region
    emailTemplate_id BIGINT, -- Specific to Email, stores email template
    sendOnSpecificDate DATE DEFAULT NULL, -- Specific to Email, stores specific date to send email on
    sendDaily BOOLEAN NOT NULL DEFAULT false, -- Specific to Email, flag for daily sending
    sendMonthly BOOLEAN NOT NULL DEFAULT false, -- Specific to Email, flag for monthly sending
    sendYearly BOOLEAN NOT NULL DEFAULT false, -- Specific to Email, flag for yearly sending
    sendDuringHoliday BOOLEAN NOT NULL DEFAULT false, -- Specific to Email, flag for holiday sending
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- Inherited from NameWithDeleteEntity, for logical deletion
    createdDate TIMESTAMP, -- Inherited from BaseEntity (auditing)
    createdBy VARCHAR(50), -- Inherited from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- Inherited from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- Inherited from BaseEntity (auditing)
    FOREIGN KEY (emailTemplate_id) REFERENCES CorpConnect.COMPANY_EMAIL_TEMPLATES(id) ON DELETE SET NULL -- Foreign key constraint
);
