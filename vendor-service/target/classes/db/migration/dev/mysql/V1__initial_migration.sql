
CREATE TABLE businesses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    registration_name VARCHAR(50) NOT NULL,
    trading_name VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    email_verified BOOLEAN DEFAULT FALSE,
    phone_added BOOLEAN DEFAULT FALSE,
    address_added BOOLEAN DEFAULT FALSE,
    certificate_added BOOLEAN DEFAULT FALSE
);

CREATE TABLE contact_persons (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    role INT NOT NULL,
    phone_added BOOLEAN DEFAULT FALSE,
    emergency_phone_added BOOLEAN DEFAULT FALSE,
    id_proof_added BOOLEAN DEFAULT FALSE
);

CREATE TABLE banks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    account_name VARCHAR(50) NOT NULL,
    bank_name VARCHAR(50) NOT NULL,
    account_number VARCHAR(10) NOT NULL,
    account_type INT NOT NULL,
    address_added BOOLEAN DEFAULT FALSE
);

CREATE TABLE vendors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    uid VARCHAR(15) NOT NULL,
    user_id BIGINT NOT NULL UNIQUE,
    approval_status INT NOT NULL,
    business_id BIGINT,
    contact_person_id BIGINT,
    bank_id BIGINT,
    registered_at DATETIME NOT NULL,
    INDEX idx_id (id),
    FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE RESTRICT,
    FOREIGN KEY (contact_person_id) REFERENCES contact_persons(id) ON DELETE RESTRICT,
    FOREIGN KEY (bank_id) REFERENCES banks(id) ON DELETE RESTRICT
);

CREATE TABLE unresolved (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    reason VARCHAR(4) NOT NULL,
	queried BOOLEAN DEFAULT FALSE,
    generated_at DATETIME NOT NULL,
    vendor_id BIGINT,
    INDEX idx_id (id),
    FOREIGN KEY (vendor_id) REFERENCES vendors(id) ON DELETE CASCADE
);

-- Stored Procedure for deleting a vendor and parents
DELIMITER $$

CREATE PROCEDURE delete_vendor(IN sp_user_id BIGINT)
BEGIN

    -- Creating 3 empty boxes to temporarily hold information
    DECLARE box_business_id BIGINT;         -- Box for business ID
    DECLARE box_contact_person_id BIGINT;   -- Box for contact person ID
    DECLARE box_bank_id BIGINT;             -- Box for bank ID

    SELECT business_id, contact_person_id, bank_id
    INTO box_business_id, box_contact_person_id, box_bank_id
    FROM vendors
    WHERE user_id = sp_user_id
    FOR UPDATE;

    START TRANSACTION;

    DELETE FROM unresolved WHERE vendor_id = (SELECT id FROM vendors WHERE user_id = sp_user_id);
    DELETE FROM vendors WHERE user_id = sp_user_id;
    DELETE FROM businesses WHERE id = box_business_id;
    DELETE FROM contact_persons WHERE id = box_contact_person_id;
    DELETE FROM banks WHERE id = box_bank_id;

    COMMIT;

END$$

DELIMITER ;