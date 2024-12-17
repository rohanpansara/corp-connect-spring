CREATE TABLE IF NOT EXISTS COMPANY_EMAIL_TEMPLATES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- From NameWithDeleteEntity
    name VARCHAR(150) NOT NULL UNIQUE, -- From NameWithDeleteEntity
    subject VARCHAR(255) NOT NULL, -- Subject field for the email template
    body LONGTEXT NOT NULL, -- Body field for the email template
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- From NameWithDeleteEntity
    createdDate TIMESTAMP, -- Auditing field
    createdBy VARCHAR(50), -- Auditing field
    lastUpdatedDate TIMESTAMP, -- Auditing field
    lastUpdatedBy VARCHAR(50) -- Auditing field
);
