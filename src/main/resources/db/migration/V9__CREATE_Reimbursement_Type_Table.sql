CREATE TABLE IF NOT EXISTS CorpConnect.HR_REIMBURSEMENT_TYPE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from BaseEntity
    name VARCHAR(150) NOT NULL, -- Inherited from NameWithDeleteEntity
    description TEXT, -- Specific to ReimbursementType
    limitAmount BIGINT, -- Specific to ReimbursementType, limit amount for reimbursement
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- Inherited from NameWithDeleteEntity, for logical deletion
    createdDate TIMESTAMP, -- Inherited from BaseEntity (auditing)
    createdBy VARCHAR(50), -- Inherited from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- Inherited from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50) -- Inherited from BaseEntity (auditing)
);