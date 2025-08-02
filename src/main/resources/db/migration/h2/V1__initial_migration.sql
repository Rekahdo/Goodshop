
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NULL,
  UNIQUE (username, email)
);

CREATE TABLE authorities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL,
    assigned_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE forgot_passwords (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    otp BIGINT NOT NULL,
    user_id BIGINT NOT NULL UNIQUE,
    expire_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE profiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(55) NULL,
    middle_name VARCHAR(55) NULL,
    last_name VARCHAR(55) NULL,
    is_male BOOLEAN NOT NULL,
    phone_number VARCHAR(15) NULL UNIQUE,
    date_of_birth DATE NULL,
    bio TEXT NULL,
    user_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE addresses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    house_number TINYINT NOT NULL,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(100) NOT NULL,
    zip_code VARCHAR(20) NULL,
    phone_number VARCHAR(15) NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

--CREATE TABLE cards (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    number VARCHAR(19) NOT NULL UNIQUE,
--    cvv VARCHAR(4) NOT NULL,
--    type VARCHAR(50) NOT NULL,
--    expire_date DATE NOT NULL,
--    name VARCHAR(255) NOT NULL,
--    user_id BIGINT NOT NULL,
--    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
--);

CREATE TABLE categories (
    id TINYINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    description TEXT NOT NULL,
    category_id TINYINT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);

--CREATE TABLE wishlist (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    user_id BIGINT NOT NULL,
--    product_id BIGINT NOT NULL,
--    added_at DATETIME DEFAULT CURRENT_TIMESTAMP,
--    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
--    FOREIGN KEY (product_id) REFERENCES products(id)
--);

--CREATE TABLE coupons (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    code VARCHAR(50) NOT NULL UNIQUE,
--    discount DECIMAL(5, 2) NOT NULL CHECK (discount > 0 AND discount <= 100),
--    valid_until DATE NOT NULL,
--    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
--);

--CREATE TABLE cart (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    quantity INT NOT NULL CHECK (quantity > 0),
--    discount DECIMAL(10, 2) DEFAULT 0,
--    user_id BIGINT NOT NULL,
--    product_id BIGINT NOT NULL,
--    ship_add_id BIGINT NULL,
--    card_id BIGINT NULL,
--    coupon_id BIGINT NULL,
--    added_at DATETIME DEFAULT CURRENT_TIMESTAMP,
--    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
--    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE SET NULL,
--    FOREIGN KEY (ship_add_id) REFERENCES addresses(id) ON DELETE CASCADE,
--    FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE SET NULL,
--    FOREIGN KEY (coupon_id) REFERENCES coupons(id) ON DELETE SET NULL
--);