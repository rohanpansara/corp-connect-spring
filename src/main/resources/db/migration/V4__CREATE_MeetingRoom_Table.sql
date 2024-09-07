CREATE TABLE MEETING_ROOMS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from BaseEntity
    name VARCHAR(255) NOT NULL, -- Inherited from NameWithDeleteEntity
    floor_number VARCHAR(50) NOT NULL, -- Floor number where the meeting room is located
    capacity INT, -- Maximum number of people the room can accommodate
    equipment LONGTEXT, -- Information about equipment available in the room
    is_deleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the room is logically deleted
    point_of_contact_id BIGINT, -- Foreign key to User (contact person for the room)
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    FOREIGN KEY (point_of_contact_id) REFERENCES USERS(id) -- Foreign key constraint
);
