CREATE TABLE IF NOT EXISTS CorpConnect.HR_WORK_SHIFTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from NamedEntity
    name VARCHAR(150) NOT NULL UNIQUE, -- Inherited from NamedEntity
    duration VARCHAR(20), -- Duration of the shift (e.g., "8 hours")
    start_time TIME, -- Start time of the shift
    end_time TIME, -- End time of the shift
    deleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the schedule is logically deleted
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50) -- from BaseEntity (auditing)
);
