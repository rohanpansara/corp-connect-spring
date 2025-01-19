CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_PERSONAL (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    gender VARCHAR(50) NOT NULL, -- Enum type for Gender
    birthdate DATE,
    contact_number VARCHAR(10),
    address VARCHAR(255),
    personal_email VARCHAR(255),
    emergency_contact_name VARCHAR(50),
    emergency_contact_number VARCHAR(10),
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_personal_detail FOREIGN KEY (user_id) REFERENCES `USERS`(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id) -- Index for faster lookups
);
