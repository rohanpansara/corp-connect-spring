CREATE TABLE IF NOT EXISTS CorpConnect.COMPANY_OTP (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- assuming id is inherited from BaseEntity
    otp VARCHAR(6) NOT NULL UNIQUE,
    type VARCHAR(30), -- otp type
    user_id BIGINT, -- Foreign key to User table
    verified BOOLEAN NOT NULL DEFAULT false, -- Indicates if the OTP is verified by the user
    expired BOOLEAN NOT NULL DEFAULT false, -- Indicates if the OTP is expired or not
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    CONSTRAINT fk_otp_user FOREIGN KEY (user_id) REFERENCES `USERS`(id) ON DELETE CASCADE -- Automatically delete OTPs when a user is deleted
);
