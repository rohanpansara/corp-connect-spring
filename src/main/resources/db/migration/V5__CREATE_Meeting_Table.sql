CREATE TABLE IF NOT EXISTS CorpConnect.HR_MEETINGS (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY, -- Inherited from NameWithDeleteEntity
    `name` VARCHAR(150) NOT NULL, -- Inherited from NameWithDeleteEntity
    deleted BOOLEAN NOT NULL DEFAULT false, -- Inherited from NameWithDeleteEntity
    organizer_id BIGINT NOT NULL, -- Foreign key referencing USERS (organizer of the meeting)
    meeting_room_id BIGINT, -- Foreign key referencing HR_MEETING_ROOMS
    start_time TIMESTAMP, -- Meeting start time
    end_time TIMESTAMP, -- Meeting end time
    `description` VARCHAR(500), -- Description of the meeting
    `status` VARCHAR(50) NOT NULL, -- Status of the meeting (e.g., Scheduled, Cancelled)
    recurring BOOLEAN NOT NULL DEFAULT false, -- Indicates if the meeting is recurring
    recurrence_pattern VARCHAR(50) NOT NULL, -- Pattern of recurrence (e.g., DAILY, WEEKLY)
    `virtual` BOOLEAN NOT NULL DEFAULT false, -- Indicates if the meeting is virtual
    `secured` BOOLEAN NOT NULL DEFAULT false, -- Indicates if the meeting is secured
    access_code VARCHAR(50), -- Access code for secured meetings
    created_date TIMESTAMP, -- Auditing field
    created_by VARCHAR(50), -- Auditing field
    last_updated_date TIMESTAMP, -- Auditing field
    last_updated_by VARCHAR(50), -- Auditing field
    FOREIGN KEY (organizer_id) REFERENCES CorpConnect.USERS(id) ON DELETE CASCADE,
    FOREIGN KEY (meeting_room_id) REFERENCES CorpConnect.HR_MEETING_ROOMS(id) ON DELETE SET NULL
);
