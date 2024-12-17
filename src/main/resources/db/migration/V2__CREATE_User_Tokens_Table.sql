CREATE TABLE IF NOT EXISTS CorpConnect.USER_TOKENS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- From BaseEntity
    token LONGTEXT, -- From Token
    user_id BIGINT, -- Foreign key to User table
    provider VARCHAR(20), -- From Token
    expireTime TIMESTAMP NOT NULL, -- From Token
    isExpired BOOLEAN NOT NULL DEFAULT false, -- From Token
    createdDate TIMESTAMP, -- Auditing field
    createdBy VARCHAR(50), -- Auditing field
    lastUpdatedDate TIMESTAMP, -- Auditing field
    lastUpdatedBy VARCHAR(50), -- Auditing field
    CONSTRAINT fk_token_user FOREIGN KEY (user_id) REFERENCES `USERS`(id) -- Foreign key constraint to USER table
);