CREATE TABLE IF NOT EXISTS CorpConnect.USERS_ATTENDANCE_DETAILS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    `date` DATE NOT NULL,
    `status` VARCHAR(50) DEFAULT 'ABSENT' NOT NULL,
    totalHours DECIMAL(5, 2) DEFAULT 0.0 NOT NULL,
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_attendance FOREIGN KEY (user_id) REFERENCES `USERS`(id) ON DELETE CASCADE
);
