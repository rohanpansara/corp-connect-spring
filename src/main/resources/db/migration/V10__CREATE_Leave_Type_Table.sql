CREATE TABLE IF NOT EXISTS CorpConnect.HR_LEAVE_TYPE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- From NameEntity
    name VARCHAR(50) NOT NULL UNIQUE, -- From NameEntity
    paid_leave BOOLEAN NOT NULL DEFAULT true, -- Indicates if the leave is paid or unpaid
    max_leaves_per_month INT DEFAULT 1, -- Maximum number of leaves a user can take in a month
    max_leaves_per_year INT DEFAULT 6, -- Maximum number of leaves a user can take in a year
    max_leave_requests_per_month INT DEFAULT 1, -- Maximum number of leave requests a user can submit in a month
    max_leave_requests_per_year INT DEFAULT 12, -- Maximum number of leave requests a user can submit in a year
    monthly_accrued_leaves INT DEFAULT 0, -- Number of leaves accrued each month
    carry_forward BOOLEAN NOT NULL DEFAULT false, -- Indicates if unused leaves can be carried forward
    max_carry_forward_leaves INT DEFAULT 0, -- Maximum number of leaves that can be carried forward
    is_half_day_allowed BOOLEAN NOT NULL DEFAULT false, -- Indicates if half-day leave is permitted
    is_negative_balance_allowed BOOLEAN NOT NULL DEFAULT false, -- Indicates if leave requests can result in a negative balance
    prior_notice_days INT DEFAULT 3, -- Number of days prior notice required
    allowed_in_probation BOOLEAN NOT NULL DEFAULT false, -- Indicates if leave is allowed during probation
    document_required BOOLEAN NOT NULL DEFAULT false, -- Indicates if supporting documents are required
    applicable_gender VARCHAR(15), -- Gender restriction (if any, e.g., 'MALE', 'FEMALE', 'ANY')
    deleted BOOLEAN NOT NULL DEFAULT false, -- From NameEntity
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50) -- from BaseEntity (auditing)
);
