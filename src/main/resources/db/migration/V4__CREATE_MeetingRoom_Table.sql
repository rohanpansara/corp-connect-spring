CREATE TABLE IF NOT EXISTS ESS.HR_MEETING_ROOMS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from BaseEntity
    name VARCHAR(150) NOT NULL, -- Inherited from NameWithDeleteEntity
    buildingName VARCHAR(150),
    floorNumber INT NOT NULL, -- Floor number where the meeting room is located
    point_of_contact_id BIGINT, -- Foreign key to User (contact person for the room)
    capacity INT, -- Maximum number of people the room can accommodate
    equipment LONGTEXT, -- Information about equipment available in the room
    isDeleted BOOLEAN NOT NULL DEFAULT false, -- Indicates if the room is logically deleted
    createdDate TIMESTAMP, -- from BaseEntity (auditing)
    createdBy VARCHAR(50), -- from BaseEntity (auditing)
    lastUpdatedDate TIMESTAMP, -- from BaseEntity (auditing)
    lastUpdatedBy VARCHAR(50), -- from BaseEntity (auditing)
    FOREIGN KEY fk_point_of_contact (point_of_contact_id) REFERENCES `USER`(id) -- Foreign key constraint
);
