CREATE TABLE IF NOT EXISTS CorpConnect.USERS_LEAVE_DETAILS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- From BaseEntity
    user_id BIGINT NOT NULL, -- Foreign key to the User table (who made the request)
    requestStatus VARCHAR(50) NOT NULL, -- Current status of the leave request (e.g., PENDING, APPROVED)
    leave_type_id BIGINT NOT NULL, -- Foreign key to the LeaveType table
    startDate DATE NOT NULL, -- Start date of the leave
    endDate DATE NOT NULL, -- End date of the leave
    totalDays INT NOT NULL, -- Total number of leave days requested
    approving_manager_id BIGINT, -- Foreign key to the User table (manager who approved the request)
    createdDate TIMESTAMP, -- Auditing field
    createdBy VARCHAR(50), -- Auditing field
    lastUpdatedDate TIMESTAMP, -- Auditing field
    lastUpdatedBy VARCHAR(50), -- Auditing field
    CONSTRAINT fk_requested_by_user FOREIGN KEY (user_id) REFERENCES `USERS`(id),
    CONSTRAINT fk_leave_type FOREIGN KEY (leave_type_id) REFERENCES HR_LEAVE_TYPE(id),
    CONSTRAINT fk_approving_manager FOREIGN KEY (approving_manager_id) REFERENCES `USERS`(id)
);
