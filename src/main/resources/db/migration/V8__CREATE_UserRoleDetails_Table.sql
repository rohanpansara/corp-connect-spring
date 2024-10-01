CREATE TABLE IF NOT EXISTS ESS.USER_ROLE_DETAILS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    userStatus VARCHAR(50) NOT NULL, -- Enum type for UserStatus
    current_job_title_id BIGINT, -- Foreign key to JOB_TITLES table
    onboardingDate DATE,
    department VARCHAR(255),
    reportingManager VARCHAR(255),
    probationPeriod VARCHAR(50),
    probationEndDate DATE,
    workShift VARCHAR(50),
    currentProjects VARCHAR(255),
    currentJobResponsibilities VARCHAR(255),
    workLocation VARCHAR(255),
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_role FOREIGN KEY (user_id) REFERENCES `USER`(id) ON DELETE CASCADE,
    CONSTRAINT fk_current_job_title FOREIGN KEY (current_job_title_id) REFERENCES HR_JOB_TITLES(id)
);
