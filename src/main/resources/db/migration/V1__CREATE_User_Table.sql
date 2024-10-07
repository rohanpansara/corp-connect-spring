CREATE TABLE IF NOT EXISTS CorpConnect.`USER` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- from BaseEntity
    name VARCHAR(150), -- from NamedEntity
    email VARCHAR(255) NOT NULL UNIQUE, -- from User
    password VARCHAR(255) NOT NULL, -- from User
    roles VARCHAR(50) NOT NULL, -- Enum type (UserRole)
    loginAttempts INT DEFAULT 0, -- from User
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the user is soft deleted
    isAccountNonExpired BOOLEAN NOT NULL, -- from User
    isAccountNonLocked BOOLEAN NOT NULL, -- from User
    isCredentialsNonExpired BOOLEAN NOT NULL, -- from User
    isAccountEnabled BOOLEAN NOT NULL, -- from User
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    INDEX IDX_NAME (name) -- index on the name field
);
