CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_DEPARTMENT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from BaseEntity
    department_id BIGINT, -- Foreign key referencing HR_DEPARTMENTS
    user_id BIGINT, -- Foreign key referencing USERS
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)

    -- Foreign key constraints
    FOREIGN KEY (department_id) REFERENCES HR_DEPARTMENTS(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE,

    -- Unique constraint to prevent duplicate user-department pairs
    CONSTRAINT unique_user_department UNIQUE (department_id, user_id)
);