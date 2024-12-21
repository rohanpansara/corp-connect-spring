CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_PUNCH (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    punchTime TIMESTAMP NOT NULL,
    punchType VARCHAR(50) DEFAULT 'FIRST_IN' NOT NULL, -- Default value for punch type
    location_id BIGINT NOT NULL,
    allowed BOOLEAN NOT NULL DEFAULT true,
    remarks VARCHAR(255),
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_punches FOREIGN KEY (user_id) REFERENCES `USERS`(id) ON DELETE CASCADE,
    CONSTRAINT fk_location_punches FOREIGN KEY (location_id) REFERENCES `HR_LOCATIONS`(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id) -- Index for faster lookups
);
