CREATE TABLE IF NOT EXISTS CorpConnect.HR_JOB_TITLES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- assuming id is inherited from NameWithDeleteEntity
    name VARCHAR(150) NOT NULL UNIQUE, -- assuming name is inherited from NameWithDeleteEntity
    grade VARCHAR(50), -- grade field
    description LONGTEXT, -- description field
    deleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the job title is logically deleted
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50) -- from BaseEntity (auditing)
);
