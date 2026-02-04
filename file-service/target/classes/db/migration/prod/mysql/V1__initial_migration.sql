
CREATE TABLE files (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    name VARCHAR(255),
    type VARCHAR(255),
    purpose INT NOT NULL,
    file_size VARCHAR(10),
    file_data LONGBLOB,
    updated_at DATETIME,
    INDEX idx_user_id_name (user_id, name),
    INDEX idx_user_id_purpose (user_id, purpose)
)