CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_DEPARTMENT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from BaseEntity
    department_id BIGINT, -- Foreign key referencing HR_DEPARTMENTS
    user_id BIGINT, -- Foreign key referencing USERS
    createdDate TIMESTAMP, -- Inherited from BaseEntity (auditing)
    createdBy VARCHAR(50), -- Inherited from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- Inherited from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- Inherited from BaseEntity (auditing)

    -- Foreign key constraints
    FOREIGN KEY (department_id) REFERENCES HR_DEPARTMENTS(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE,

    -- Unique constraint to prevent duplicate user-department pairs
    CONSTRAINT unique_user_department UNIQUE (department_id, user_id)
);