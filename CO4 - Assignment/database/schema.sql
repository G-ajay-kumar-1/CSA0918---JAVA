CREATE DATABASE IF NOT EXISTS campus_events
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE campus_events;

CREATE TABLE IF NOT EXISTS registrations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    event_name VARCHAR(80) NOT NULL,
    registered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_student_event UNIQUE (student_id, event_name)
);

INSERT INTO registrations (student_id, student_name, event_name)
VALUES ('STU1001', 'Sample Student', 'Tech Fest')
ON DUPLICATE KEY UPDATE student_name = VALUES(student_name);
