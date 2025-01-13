CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_ROLE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    reporting_manager_id BIGINT, -- Foreign key to USER table
    user_status VARCHAR(50) NOT NULL, -- Enum type for UserStatus
    onboarding_date DATE,
    probation_period VARCHAR(50),
    probation_end_date DATE,
    current_job_title_id BIGINT, -- Foreign key to HR_JOB_TITLES table
    department_id BIGINT, -- Foreign key to HR_DEPARTMENTS table
    work_shift_id BIGINT, -- Foreign key to HR_WORK_SHIFTS table
    current_projects VARCHAR(255),
    current_job_responsibilities VARCHAR(255),
    work_location VARCHAR(255),
    referred_by BIGINT NOT NULL, -- Foreign key to USER table
    deleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the role detail is logically deleted
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_role_detail FOREIGN KEY (user_id) REFERENCES `USERS`(id) ON DELETE CASCADE,
    CONSTRAINT fk_reporting_manager FOREIGN KEY (reporting_manager_id) REFERENCES `USERS`(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_referred_by FOREIGN KEY (referred_by) REFERENCES `USERS`(id) ON DELETE CASCADE,
    CONSTRAINT fk_current_job_title FOREIGN KEY (current_job_title_id) REFERENCES HR_JOB_TITLES(id),
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES HR_DEPARTMENTS(id),
    CONSTRAINT fk_work_shift FOREIGN KEY (work_shift_id) REFERENCES HR_WORK_SHIFTS(id),
    INDEX idx_user_id (user_id) -- Index for faster lookups
);
