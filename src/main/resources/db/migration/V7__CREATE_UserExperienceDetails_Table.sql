CREATE TABLE IF NOT EXISTS USER_EXPERIENCE_DETAILS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    previous_company VARCHAR(150),
    previous_company_location VARCHAR(255),
    previous_job_title VARCHAR(150),
    previous_manager VARCHAR(150),
    previous_manager_contact VARCHAR(13),
    experience_years FLOAT,
    job_responsibilities LONGTEXT,
    technologies_worked_on LONGTEXT,
    previous_job_start_date DATE,
    previous_job_end_date DATE,
    reason_for_leaving VARCHAR(150),
    considered_role VARCHAR(150),
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_experience FOREIGN KEY (user_id) REFERENCES `USER`(id) ON DELETE CASCADE
);

