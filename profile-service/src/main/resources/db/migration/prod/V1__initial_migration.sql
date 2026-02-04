
CREATE TABLE profiles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL UNIQUE,
    fn VARCHAR(20),
    mn VARCHAR(20),
    ln VARCHAR(20),
    dob DATE,
    gender INT
);