CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_PERSONAL (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    gender VARCHAR(50) NOT NULL, -- Enum type for Gender
    birthdate DATE,
    contactNumber VARCHAR(10),
    address VARCHAR(255),
    personalEmail VARCHAR(255),
    emergencyContactName VARCHAR(50),
    emergencyContactNumber VARCHAR(10),
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_personal_detail FOREIGN KEY (user_id) REFERENCES `USERS`(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id) -- Index for faster lookups
);
