CREATE TABLE IF NOT EXISTS ESS.USER_EXPERIENCE_DETAILS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to USER table
    previousCompany VARCHAR(150),
    previousCompanyLocation VARCHAR(255),
    previousJobTitle VARCHAR(150),
    previousManager VARCHAR(150),
    previousManagerContact VARCHAR(13),
    experienceYears FLOAT,
    jobResponsibilities LONGTEXT,
    technologiesWorkedOn LONGTEXT,
    previousJobStartDate DATE,
    previousJobEndDate DATE,
    reasonForLeaving VARCHAR(150),
    consideredRole VARCHAR(150),
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_user_experience FOREIGN KEY (user_id) REFERENCES `USER`(id) ON DELETE CASCADE
);

