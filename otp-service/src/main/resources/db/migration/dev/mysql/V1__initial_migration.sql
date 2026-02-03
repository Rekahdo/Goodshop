
CREATE TABLE otps (
    id INT PRIMARY KEY AUTO_INCREMENT,
    otp INT NOT NULL,
    purpose INT NOT NULL,
    user_id BIGINT NULL,
    sent_to VARCHAR(255) NOT NULL,
    sent_to_email BOOLEAN DEFAULT FALSE NOT NULL,
    verified BOOLEAN DEFAULT FALSE NOT NULL,
    expire_at DATETIME NOT NULL,
    INDEX idx_otp (otp),
    INDEX idx_sent_to (sent_to)
);
