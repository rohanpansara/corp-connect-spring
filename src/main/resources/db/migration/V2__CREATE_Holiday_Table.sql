CREATE TABLE IF NOT EXISTS HR_HOLIDAYS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    name VARCHAR(150) NOT NULL UNIQUE, -- from NamedEntity
    date DATE NOT NULL UNIQUE, -- LocalDate (holiday date)
    description LONGTEXT, -- Description of the holiday
    type VARCHAR(50), -- Enum type (HolidayType)
    is_recurring BOOLEAN NOT NULL DEFAULT false, -- Indicates if the holiday is recurring
    is_deleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the holiday is soft deleted
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50) -- from BaseEntity (auditing)
);
