CREATE TABLE IF NOT EXISTS CorpConnect.HR_HOLIDAYS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    name VARCHAR(150) NOT NULL UNIQUE, -- from NamedEntity
    `date` DATE NOT NULL UNIQUE, -- LocalDate (holiday date)
    description LONGTEXT, -- Description of the holiday
    type VARCHAR(50), -- Enum type (HolidayType)
    isRecurring BOOLEAN NOT NULL DEFAULT false, -- Indicates if the holiday is recurring
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50) -- from BaseEntity (auditing)
);
