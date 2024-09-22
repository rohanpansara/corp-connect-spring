CREATE TABLE IF NOT EXISTS USER_ROLE_DETAILS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    user_status VARCHAR(50) NOT NULL, -- Enum type for UserStatus
    current_job_title_id BIGINT, -- Foreign key to JOB_TITLES table
    onboarding_date DATE,
    department VARCHAR(255),
    reporting_manager VARCHAR(255),
    probation_period VARCHAR(50),
    probation_end_date DATE,
    work_shift VARCHAR(50),
    current_projects VARCHAR(255),
    current_job_responsibilities VARCHAR(255),
    work_location VARCHAR(255),
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_role FOREIGN KEY (user_id) REFERENCES `USER`(id) ON DELETE CASCADE,
    CONSTRAINT fk_current_job_title FOREIGN KEY (current_job_title_id) REFERENCES HR_JOB_TITLES(id)
);
