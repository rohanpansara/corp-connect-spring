CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_EXPERIENCE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    previous_company VARCHAR(150),
    previous_company_location VARCHAR(255),
    previous_job_title VARCHAR(150),
    previous_manager VARCHAR(150),
    previous_manager_contact_number VARCHAR(13),
    experience_years FLOAT,
    job_responsibilities LONGTEXT,
    technologies_worked_on LONGTEXT,
    previous_job_start_date DATE,
    previous_job_end_date DATE,
    reason_for_leaving VARCHAR(150),
    considered_role_id BIGINT,
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_experience_detail FOREIGN KEY (user_id) REFERENCES `USERS`(id) ON DELETE CASCADE,
    CONSTRAINT fk_considered_role FOREIGN KEY (considered_role_id) REFERENCES HR_JOB_TITLES(id),
    INDEX idx_user_id (user_id) -- Index for faster lookups
);

