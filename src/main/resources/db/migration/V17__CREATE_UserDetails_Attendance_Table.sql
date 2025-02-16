CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_ATTENDANCE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    `date` DATE NOT NULL,
    `status` VARCHAR(50) DEFAULT 'ABSENT' NOT NULL,
    total_hours DECIMAL(5, 2) DEFAULT 0.0 NOT NULL,
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_attendance FOREIGN KEY (user_id) REFERENCES `USERS`(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id) -- Index for faster lookups
);
