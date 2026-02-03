
-- Create users table
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    uid VARCHAR(15),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    verified BOOLEAN DEFAULT FALSE,
    created_at DATETIME NOT NULL,
    INDEX idx_id (id),
    INDEX idx_uid (uid),
    INDEX idx_username (username),
    INDEX idx_email (email)
);