CREATE TABLE IF NOT EXISTS CorpConnect.HR_LOCATIONS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    name VARCHAR(255) NOT NULL, -- from NameWithDeleteEntity
    address VARCHAR(255), -- Address field
    punch_allowed BOOLEAN DEFAULT FALSE, -- boolean for punchAllowed
    deleted BOOLEAN DEFAULT FALSE NOT NULL, -- Mark for soft delete
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT uq_location_name UNIQUE (name) -- Enforcing uniqueness of location name
);
