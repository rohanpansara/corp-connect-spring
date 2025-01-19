CREATE TABLE IF NOT EXISTS CorpConnect.COMPANY_CONFIGURATIONS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    name VARCHAR(255) NOT NULL UNIQUE, -- configName field
    max_val VARCHAR(255), -- maxValue field
    min_val VARCHAR(255), -- minValue field
    enabled BOOLEAN NOT NULL DEFAULT false, -- isEnabled field, defaulting to true
    deleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the config is logically deleted
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50) -- from BaseEntity (auditing)
);
