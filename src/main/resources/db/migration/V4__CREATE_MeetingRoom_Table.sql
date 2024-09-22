CREATE TABLE IF NOT EXISTS HR_MEETING_ROOMS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from BaseEntity
    name VARCHAR(150) NOT NULL, -- Inherited from NameWithDeleteEntity
    building_name VARCHAR(150),
    floor_number INT NOT NULL, -- Floor number where the meeting room is located
    point_of_contact_id BIGINT, -- Foreign key to User (contact person for the room)
    capacity INT, -- Maximum number of people the room can accommodate
    equipment LONGTEXT, -- Information about equipment available in the room
    is_deleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the room is logically deleted
    created_date TIMESTAMP, -- from BaseEntity (auditing)
    created_by VARCHAR(50), -- from BaseEntity (auditing)
    last_updated_date TIMESTAMP, -- from BaseEntity (auditing)
    last_updated_by VARCHAR(50), -- from BaseEntity (auditing)
    FOREIGN KEY fk_point_of_contact (point_of_contact_id) REFERENCES `USER`(id) -- Foreign key constraint
);
