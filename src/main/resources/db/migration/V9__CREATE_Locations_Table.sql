CREATE TABLE IF NOT EXISTS CorpConnect.HR_LOCATIONS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    name VARCHAR(255) NOT NULL, -- from NameWithDeleteEntity
    address VARCHAR(255), -- Address field
    punchAllowed BOOLEAN DEFAULT FALSE, -- boolean for punchAllowed
    isDeleted BOOLEAN DEFAULT FALSE NOT NULL, -- Mark for soft delete
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT uq_location_name UNIQUE (name) -- Enforcing uniqueness of location name
);
