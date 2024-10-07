CREATE TABLE IF NOT EXISTS CorpConnect.HR_WORK_SHIFTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from NamedEntity
    name VARCHAR(150) NOT NULL, -- Inherited from NamedEntity
    duration VARCHAR(20), -- Duration of the shift (e.g., "8 hours")
    startTime TIME, -- Start time of the shift
    endTime TIME, -- End time of the shift
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the schedule is logically deleted
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50) -- from BaseEntity (auditing)
);
