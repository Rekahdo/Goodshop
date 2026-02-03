
CREATE TABLE addresses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    purpose INT NOT NULL,
    name VARCHAR(50),
    flat_no VARCHAR(30),
    house_no INT NOT NULL,
    street_id BIGINT NOT NULL,
    off_street_id BIGINT,
    closest_bus_stop_id BIGINT,
    lga_id BIGINT,
    city_id BIGINT NOT NULL,
    state_id BIGINT NOT NULL,
    country_id BIGINT NOT NULL,
    zipcode_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    INDEX idx_user_id (user_id),
    INDEX idx_zipcode_id_user_id (zipcode_id, user_id)
);

CREATE TABLE bus_stops (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    INDEX idx_name (name)
);

CREATE TABLE streets (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    INDEX idx_name (name)
);

CREATE TABLE local_government_areas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    INDEX idx_name (name)
);

CREATE TABLE cities (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    INDEX idx_name (name)
);

CREATE TABLE states (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    INDEX idx_name (name)
);

CREATE TABLE countries (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    INDEX idx_name (name)
);

CREATE TABLE zipcodes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code BIGINT UNIQUE NOT NULL,
    INDEX idx_code (code)
);