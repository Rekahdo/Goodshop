
CREATE TABLE phones (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(10) NOT NULL,
    purpose INT NOT NULL,
    verified BOOLEAN DEFAULT FALSE,
    user_id BIGINT NOT NULL,
    country_code_id BIGINT,
    INDEX idx_number (number),
    UNIQUE(purpose, user_id)
);

CREATE TABLE country_codes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    number INT UNIQUE NOT NULL,
    INDEX idx_number (number)
);