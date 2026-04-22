CREATE TABLE IF NOT EXISTS member (
                                      id VARCHAR(36) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address VARCHAR(255),
    profession VARCHAR(100),
    phone_number VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    occupation VARCHAR(20) NOT NULL,
    federation_joining_date DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS collectivity (
                                            id VARCHAR(36) PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    federation_approval BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS membership (
                                          id VARCHAR(36) PRIMARY KEY,
    member_id VARCHAR(36) NOT NULL,
    collectivity_id VARCHAR(36) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (collectivity_id) REFERENCES collectivity(id)
    );

CREATE TABLE IF NOT EXISTS mandate (
                                       id VARCHAR(36) PRIMARY KEY,
    member_id VARCHAR(36) NOT NULL,
    collectivity_id VARCHAR(36) NOT NULL,
    role VARCHAR(20) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (collectivity_id) REFERENCES collectivity(id)
    );

ALTER TABLE collectivity ADD COLUMN unique_number VARCHAR(50) UNIQUE;
ALTER TABLE collectivity ADD COLUMN unique_name VARCHAR(100) UNIQUE;

CREATE TABLE IF NOT EXISTS membership_fee (
                                              id VARCHAR(36) PRIMARY KEY,
    collectivity_id VARCHAR(36) NOT NULL,
    eligible_from DATE NOT NULL,
    frequency VARCHAR(20) NOT NULL, -- WEEKLY, MONTHLY, ANNUALLY, PUNCTUALLY
    amount DECIMAL(15,2) NOT NULL,
    label VARCHAR(255),
    status VARCHAR(20) DEFAULT 'ACTIVE', -- ACTIVE, INACTIVE
    FOREIGN KEY (collectivity_id) REFERENCES collectivity(id)
    );

CREATE TABLE IF NOT EXISTS collectivity_transaction (
    id VARCHAR(36) PRIMARY KEY,
    collectivity_id VARCHAR(36) NOT NULL,
    creation_date DATE NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    payment_mode VARCHAR(20) NOT NULL, -- CASH, MOBILE_BANKING, BANK_TRANSFER
    account_credited_id VARCHAR(36) NOT NULL, -- référence vers un compte financier
    member_debited_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (collectivity_id) REFERENCES collectivity(id),
    FOREIGN KEY (member_debited_id) REFERENCES member(id)
    );

CREATE TABLE IF NOT EXISTS member_payment (
    id VARCHAR(36) PRIMARY KEY,
    member_id VARCHAR(36) NOT NULL,
    membership_fee_id VARCHAR(36) NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    payment_mode VARCHAR(20) NOT NULL,
    transaction_id VARCHAR(36) NOT NULL,
    creation_date DATE NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (membership_fee_id) REFERENCES membership_fee(id),
    FOREIGN KEY (transaction_id) REFERENCES collectivity_transaction(id)
    );

CREATE TABLE IF NOT EXISTS financial_account (
    id VARCHAR(36) PRIMARY KEY,
    account_type VARCHAR(20) NOT NULL, -- CASH, MOBILE_BANKING, BANK_ACCOUNT
    holder_name VARCHAR(255),
    amount DECIMAL(15,2) DEFAULT 0,
    -- pour mobile banking
    mobile_service VARCHAR(20),
    mobile_number VARCHAR(20),
    -- pour compte bancaire
    bank_name VARCHAR(50),
    bank_code VARCHAR(5),
    branch_code VARCHAR(5),
    account_number VARCHAR(11),
    rib_key VARCHAR(2)
    );