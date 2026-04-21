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