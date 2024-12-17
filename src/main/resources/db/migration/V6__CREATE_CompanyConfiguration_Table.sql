CREATE TABLE IF NOT EXISTS CorpConnect.COMPANY_CONFIGURATIONS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    name VARCHAR(255) NOT NULL UNIQUE, -- configName field
    maxVal VARCHAR(255), -- maxValue field
    minVal VARCHAR(255), -- minValue field
    isEnabled BOOLEAN NOT NULL DEFAULT false, -- isEnabled field, defaulting to true
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the config is logically deleted
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50) -- from BaseEntity (auditing)
);
