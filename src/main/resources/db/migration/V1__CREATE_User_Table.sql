CREATE TABLE IF NOT EXISTS CorpConnect.`USERS` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    name VARCHAR(150), -- from NamedEntity
    email VARCHAR(255) NOT NULL UNIQUE, -- from User
    password VARCHAR(255), -- from User
    is_email_verified BOOLEAN NOT NULL DEFAULT false, -- from User
    roles VARCHAR(50) NOT NULL, -- Enum type (UserRole)
    login_attempts INT DEFAULT 0, -- from User
    deleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the user is soft deleted
    is_account_non_expired BOOLEAN NOT NULL, -- from User
    is_account_non_locked BOOLEAN NOT NULL, -- from User
    is_credentials_non_expired BOOLEAN NOT NULL, -- from User
    is_account_enabled BOOLEAN NOT NULL, -- from User
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    INDEX IDX_NAME (name) -- index on the name field
);
