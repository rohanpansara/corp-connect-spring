CREATE TABLE IF NOT EXISTS CorpConnect.COMPANY_CONFIGURATIONS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    name VARCHAR(255) NOT NULL UNIQUE, -- configName field
    configMaxValue VARCHAR(255), -- configMaxValue field
    configMinValue VARCHAR(255), -- configMinValue field
    isEnabled BOOLEAN NOT NULL DEFAULT false, -- isEnabled field, defaulting to true
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the config is logically deleted
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50) -- from BaseEntity (auditing)
);
