CREATE TABLE IF NOT EXISTS CorpConnect.USER_DETAILS_LEAVE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- From BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to the User table (who made the request)
    request_status VARCHAR(50) NOT NULL, -- Current status of the leave request (e.g., PENDING, APPROVED)
    leave_type_id BIGINT NOT NULL, -- Foreign key to the LeaveType table
    start_date DATE NOT NULL, -- Start date of the leave
    end_date DATE NOT NULL, -- End date of the leave
    total_days INT NOT NULL, -- Total number of leave days requested
    approving_manager_id BIGINT, -- Foreign key to the User table (manager who approved the request)
    remarks VARCHAR(255),
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_requested_by_user FOREIGN KEY (user_id) REFERENCES `USERS`(id),
    CONSTRAINT fk_leave_type FOREIGN KEY (leave_type_id) REFERENCES HR_LEAVE_TYPE(id),
    CONSTRAINT fk_approving_manager FOREIGN KEY (approving_manager_id) REFERENCES `USERS`(id),
    INDEX idx_user_id (user_id) -- Index for faster lookups
);
