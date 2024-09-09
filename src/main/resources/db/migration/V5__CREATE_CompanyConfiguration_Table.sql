CREATE TABLE COMPANY_CONFIGURATIONS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    config_name VARCHAR(255), -- configName field
    config_max_value VARCHAR(255), -- configMaxValue field
    config_min_value VARCHAR(255), -- configMinValue field
    is_enabled BOOLEAN NOT NULL DEFAULT true, -- isEnabled field, defaulting to true
    is_deleted BOOLEAN NOT NULL DEFAULT false, -- isDeleted field, defaulting to false
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50) -- from BaseEntity (auditing)
);
