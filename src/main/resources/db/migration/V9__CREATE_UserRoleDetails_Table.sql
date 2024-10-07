CREATE TABLE IF NOT EXISTS CorpConnect.USER_ROLE_DETAILS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    reporting_manager_id BIGINT, -- Foreign key to USER table
    userStatus VARCHAR(50) NOT NULL, -- Enum type for UserStatus
    onboardingDate DATE,
    probationPeriod VARCHAR(50),
    probationEndDate DATE,
    current_job_title_id BIGINT, -- Foreign key to HR_JOB_TITLES table
    department_id BIGINT, -- Foreign key to HR_DEPARTMENTS table
    work_shift_id BIGINT, -- Foreign key to HR_WORK_SHIFTS table
    currentProjects VARCHAR(255),
    currentJobResponsibilities VARCHAR(255),
    workLocation VARCHAR(255),
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the role detail is logically deleted
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_role_detail FOREIGN KEY (user_id) REFERENCES `USER`(id) ON DELETE CASCADE,
    CONSTRAINT fk_reporting_manager FOREIGN KEY (reporting_manager_id) REFERENCES `USER`(id) ON DELETE CASCADE,
    CONSTRAINT fk_current_job_title FOREIGN KEY (current_job_title_id) REFERENCES HR_JOB_TITLES(id),
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES HR_DEPARTMENTS(id),
    CONSTRAINT fk_work_shift FOREIGN KEY (work_shift_id) REFERENCES HR_WORK_SHIFTS(id)
);
