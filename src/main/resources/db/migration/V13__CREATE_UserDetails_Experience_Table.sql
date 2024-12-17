CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_EXPERIENCE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    previousCompany VARCHAR(150),
    previousCompanyLocation VARCHAR(255),
    previousJobTitle VARCHAR(150),
    previousManager VARCHAR(150),
    previousManagerContactNumber VARCHAR(13),
    experienceYears FLOAT,
    jobResponsibilities LONGTEXT,
    technologiesWorkedOn LONGTEXT,
    previousJobStartDate DATE,
    previousJobEndDate DATE,
    reasonForLeaving VARCHAR(150),
    considered_role_id BIGINT,
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_experience_detail FOREIGN KEY (user_id) REFERENCES `USERS`(id) ON DELETE CASCADE,
    CONSTRAINT fk_considered_role FOREIGN KEY (considered_role_id) REFERENCES HR_JOB_TITLES(id)
);

