CREATE TABLE IF NOT EXISTS CorpConnect.HR_LEAVE_TYPE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- From NameEntity
    name VARCHAR(50) NOT NULL UNIQUE, -- From NameEntity
    isPaidLeave BOOLEAN NOT NULL DEFAULT true, -- Indicates if the leave is paid or unpaid
    maxLeavesPerMonth INT DEFAULT 1, -- Maximum number of leaves a user can take in a month
    maxLeavesPerYear INT DEFAULT 6, -- Maximum number of leaves a user can take in a year
    maxLeaveRequestsPerMonth INT DEFAULT 1, -- Maximum number of leave requests a user can submit in a month
    maxLeaveRequestsPerYear INT DEFAULT 12, -- Maximum number of leave requests a user can submit in a year
    monthlyAccruedLeaves INT DEFAULT 0, -- Number of leaves accrued each month
    canCarryForward BOOLEAN NOT NULL DEFAULT false, -- Indicates if unused leaves can be carried forward
    maxCarryForwardLeaves INT DEFAULT 0, -- Maximum number of leaves that can be carried forward
    isHalfDayLeaveAllowed BOOLEAN NOT NULL DEFAULT false, -- Indicates if half-day leave is permitted
    canHaveNegativeBalance BOOLEAN NOT NULL DEFAULT false, -- Indicates if leave requests can result in a negative balance
    priorNoticeDaysRequired INT DEFAULT 3, -- Number of days prior notice required
    isAllowedDuringProbation BOOLEAN NOT NULL DEFAULT false, -- Indicates if leave is allowed during probation
    isDocumentRequired BOOLEAN NOT NULL DEFAULT false, -- Indicates if supporting documents are required
    applicableGender VARCHAR(15), -- Gender restriction (if any, e.g., 'MALE', 'FEMALE', 'ANY')
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- From NameEntity
    createdDate TIMESTAMP, -- Auditing field
    createdBy VARCHAR(50), -- Auditing field
    lastUpdatedDate TIMESTAMP, -- Auditing field
    lastUpdatedBy VARCHAR(50) -- Auditing field
);
