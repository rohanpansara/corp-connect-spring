CREATE TABLE IF NOT EXISTS COMPANY_EMAIL_TEMPLATES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- From NameWithDeleteEntity
    name VARCHAR(150) NOT NULL UNIQUE, -- From NameWithDeleteEntity
    subject VARCHAR(255) NOT NULL, -- Subject field for the email template
    body LONGTEXT NOT NULL, -- Body field for the email template
    deleted BOOLEAN NOT NULL DEFAULT false, -- From NameWithDeleteEntity
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50) -- from BaseEntity (auditing)
);
