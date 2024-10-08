CREATE TABLE IF NOT EXISTS CorpConnect.HR_DEPARTMENTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- From NameWithDeleteEntity
    name VARCHAR(150) NOT NULL, -- From NameWithDeleteEntity
    code VARCHAR(50), -- Department code
    department_head_id BIGINT, -- Foreign key to User table
    location VARCHAR(255), -- Department location
    phoneExtension VARCHAR(20), -- Phone extension
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- From NameWithDeleteEntity
    createdDate TIMESTAMP, -- Auditing field
    createdBy VARCHAR(50), -- Auditing field
    lastUpdatedDate TIMESTAMP, -- Auditing field
    lastUpdatedBy VARCHAR(50), -- Auditing field
    CONSTRAINT fk_department_head FOREIGN KEY (department_head_id) REFERENCES `USERS`(id) -- Foreign key constraint to USER table
);
