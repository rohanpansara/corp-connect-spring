CREATE TABLE IF NOT EXISTS CorpConnect.HR_DEPARTMENTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- From NameWithDeleteEntity
    name VARCHAR(150) NOT NULL UNIQUE, -- From NameWithDeleteEntity
    code VARCHAR(50), -- Department code
    department_head_id BIGINT, -- Foreign key to User table
    location VARCHAR(255), -- Department location
    phone_extension VARCHAR(20), -- Phone extension
    deleted BOOLEAN NOT NULL DEFAULT false, -- From NameWithDeleteEntity
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_department_head FOREIGN KEY (department_head_id) REFERENCES `USERS`(id) -- Foreign key constraint to USER table
);
