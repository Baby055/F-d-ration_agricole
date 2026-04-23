DELETE FROM member_payment;
DELETE FROM collectivity_transaction;
DELETE FROM membership_fee;
DELETE FROM mandate;
DELETE FROM membership;
DELETE FROM financial_account;
DELETE FROM member;
DELETE FROM collectivity;

INSERT INTO collectivity (id, location, federation_approval, unique_number, unique_name) VALUES
('col-1', 'Ambatondrazaka', true, '1', 'Mpanorina'),
('col-2', 'Ambatondrazaka', true, '2', 'Dobo voalohany'),
('col-3', 'Brickaville', true, '3', 'Tantely mamy');

INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, federation_joining_date) VALUES
('C1-M1', 'Prénom membre 1', 'Nom membre 1', '1980-02-01', 'MALE', 'Lot II V M Ambato.', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', 'PRESIDENT', '2020-01-01'),
('C1-M2', 'Prénom membre 2', 'Nom membre 2', '1982-03-05', 'MALE', 'Lot II F Ambato.', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', 'VICE_PRESIDENT', '2020-01-01'),
('C1-M3', 'Prénom membre 3', 'Nom membre 3', '1992-03-10', 'MALE', 'Lot II J Ambato.', 'Collecteur', '0331234567', 'member.3@fed-agri.mg', 'SECRETARY', '2021-01-01'),
('C1-M4', 'Prénom membre 4', 'Nom membre 4', '1988-05-22', 'FEMALE', 'Lot A K 50 Ambato.', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', 'TREASURER', '2021-01-01'),
('C1-M5', 'Prénom membre 5', 'Nom membre 5', '1999-08-21', 'MALE', 'Lot UV 80 Ambato.', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', 'SENIOR', '2022-01-01'),
('C1-M6', 'Prénom membre 6', 'Nom membre 6', '1998-08-22', 'FEMALE', 'Lot UV 6 Ambato.', 'Riziculteur', '0372234567', 'member.6@fed-agri.mg', 'SENIOR', '2022-01-01'),
('C1-M7', 'Prénom membre 7', 'Nom membre 7', '1998-01-31', 'MALE', 'Lot UV 7 Ambato.', 'Riziculteur', '0374234567', 'member.7@fed-agri.mg', 'SENIOR', '2022-01-01'),
('C1-M8', 'Prénom membre 8', 'Nom membre 8', '1975-08-20', 'MALE', 'Lot UV 8 Ambato.', 'Riziculteur', '0370234567', 'member.8@fed-agri.mg', 'SENIOR', '2022-01-01'),
('C2-M1', 'Prénom membre 1', 'Nom membre 1', '1980-02-01', 'MALE', 'Lot II V M Ambato.', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', 'SENIOR', '2020-01-01'),
('C2-M2', 'Prénom membre 2', 'Nom membre 2', '1982-03-05', 'MALE', 'Lot II F Ambato.', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', 'SENIOR', '2020-01-01'),
('C2-M3', 'Prénom membre 3', 'Nom membre 3', '1992-03-10', 'MALE', 'Lot II J Ambato.', 'Collecteur', '0331234567', 'member.3@fed-agri.mg', 'SENIOR', '2021-01-01'),
('C2-M4', 'Prénom membre 4', 'Nom membre 4', '1988-05-22', 'FEMALE', 'Lot A K 50 Ambato.', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', 'SENIOR', '2021-01-01'),
('C2-M5', 'Prénom membre 5', 'Nom membre 5', '1999-08-21', 'MALE', 'Lot UV 80 Ambato.', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', 'PRESIDENT', '2022-01-01'),
('C2-M6', 'Prénom membre 6', 'Nom membre 6', '1998-08-22', 'FEMALE', 'Lot UV 6 Ambato.', 'Riziculteur', '0372234567', 'member.6@fed-agri.mg', 'VICE_PRESIDENT', '2022-01-01'),
('C2-M7', 'Prénom membre 7', 'Nom membre 7', '1998-01-31', 'MALE', 'Lot UV 7 Ambato.', 'Riziculteur', '0374234567', 'member.7@fed-agri.mg', 'SECRETARY', '2022-01-01'),
('C2-M8', 'Prénom membre 8', 'Nom membre 8', '1975-08-20', 'MALE', 'Lot UV 8 Ambato.', 'Riziculteur', '0370234567', 'member.8@fed-agri.mg', 'TREASURER', '2022-01-01'),
('C3-M1', 'Prénom membre 9', 'Nom membre 9', '1988-01-02', 'MALE', 'Lot 3 J Antisibare', 'Apiculteur', '034034567', 'member.9@fed-agri.mg', 'PRESIDENT', '2021-06-01'),
('C3-M2', 'Prénom membre 10', 'Nom membre 10', '1982-03-05', 'MALE', 'Lot 2 J Antisibare', 'Agriculteur', '0338634567', 'member.10@fed-agri.mg', 'VICE_PRESIDENT', '2021-06-01'),
('C3-M3', 'Prénom membre 11', 'Nom membre 11', '1992-03-12', 'MALE', 'Lot 8 KM Antisibare', 'Collecteur', '0338234567', 'member.11@fed-agri.mg', 'SECRETARY', '2022-01-01'),
('C3-M4', 'Prénom membre 12', 'Nom membre 12', '1988-05-10', 'FEMALE', 'Lot 5 K 50 Antisibare', 'Distributeur', '0382334567', 'member.12@fed-agri.mg', 'TREASURER', '2022-01-01'),
('C3-M5', 'Prénom membre 13', 'Nom membre 13', '1999-08-11', 'MALE', 'Lot UV 80 Antisibare', 'Apiculteur', '0373365567', 'member.13@fed-agri.mg', 'SENIOR', '2023-01-01'),
('C3-M6', 'Prénom membre 14', 'Nom membre 14', '1998-08-09', 'FEMALE', 'Lot UV 6 Antisibare', 'Apiculteur', '0378234567', 'member.14@fed-agri.mg', 'SENIOR', '2023-01-01'),
('C3-M7', 'Prénom membre 15', 'Nom membre 15', '1998-01-13', 'MALE', 'Lot UV 7 Antisibare', 'Apiculteur', '0374914567', 'member.15@fed-agri.mg', 'SENIOR', '2023-01-01'),
('C3-M8', 'Prénom membre 16', 'Nom membre 16', '1975-08-02', 'MALE', 'Lot UV 8 Antisibare', 'Apiculteur', '0370634567', 'member.16@fed-agri.mg', 'SENIOR', '2023-01-01');

INSERT INTO membership (id, member_id, collectivity_id, start_date, end_date) VALUES
(UUID(), 'C1-M1', 'col-1', '2020-01-01', NULL),
(UUID(), 'C1-M2', 'col-1', '2020-01-01', NULL),
(UUID(), 'C1-M3', 'col-1', '2021-01-01', NULL),
(UUID(), 'C1-M4', 'col-1', '2021-01-01', NULL),
(UUID(), 'C1-M5', 'col-1', '2022-01-01', NULL),
(UUID(), 'C1-M6', 'col-1', '2022-01-01', NULL),
(UUID(), 'C1-M7', 'col-1', '2022-01-01', NULL),
(UUID(), 'C1-M8', 'col-1', '2022-01-01', NULL),

(UUID(), 'C2-M1', 'col-2', '2026-01-01', NULL),
(UUID(), 'C2-M2', 'col-2', '2026-01-01', NULL),
(UUID(), 'C2-M3', 'col-2', '2026-01-01', NULL),
(UUID(), 'C2-M4', 'col-2', '2026-01-01', NULL),
(UUID(), 'C2-M5', 'col-2', '2026-01-01', NULL),
(UUID(), 'C2-M6', 'col-2', '2026-01-01', NULL),
(UUID(), 'C2-M7', 'col-2', '2026-01-01', NULL),
(UUID(), 'C2-M8', 'col-2', '2026-01-01', NULL),
(UUID(), 'C3-M1', 'col-3', '2021-06-01', NULL),
(UUID(), 'C3-M2', 'col-3', '2021-06-01', NULL),
(UUID(), 'C3-M3', 'col-3', '2022-01-01', NULL),
(UUID(), 'C3-M4', 'col-3', '2022-01-01', NULL),
(UUID(), 'C3-M5', 'col-3', '2023-01-01', NULL),
(UUID(), 'C3-M6', 'col-3', '2023-01-01', NULL),
(UUID(), 'C3-M7', 'col-3', '2023-01-01', NULL),
(UUID(), 'C3-M8', 'col-3', '2023-01-01', NULL);


INSERT INTO mandate (id, member_id, collectivity_id, role, start_date, end_date) VALUES
(UUID(), 'C1-M1', 'col-1', 'PRESIDENT', '2026-01-01', '2026-12-31'),
(UUID(), 'C1-M2', 'col-1', 'VICE_PRESIDENT', '2026-01-01', '2026-12-31'),
(UUID(), 'C1-M3', 'col-1', 'SECRETARY', '2026-01-01', '2026-12-31'),
(UUID(), 'C1-M4', 'col-1', 'TREASURER', '2026-01-01', '2026-12-31'),
(UUID(), 'C2-M5', 'col-2', 'PRESIDENT', '2026-01-01', '2026-12-31'),
(UUID(), 'C2-M6', 'col-2', 'VICE_PRESIDENT', '2026-01-01', '2026-12-31'),
(UUID(), 'C2-M7', 'col-2', 'SECRETARY', '2026-01-01', '2026-12-31'),
(UUID(), 'C2-M8', 'col-2', 'TREASURER', '2026-01-01', '2026-12-31'),
(UUID(), 'C3-M1', 'col-3', 'PRESIDENT', '2026-01-01', '2026-12-31'),
(UUID(), 'C3-M2', 'col-3', 'VICE_PRESIDENT', '2026-01-01', '2026-12-31'),
(UUID(), 'C3-M3', 'col-3', 'SECRETARY', '2026-01-01', '2026-12-31'),
(UUID(), 'C3-M4', 'col-3', 'TREASURER', '2026-01-01', '2026-12-31');


INSERT INTO membership_fee (id, collectivity_id, eligible_from, frequency, amount, label, status) VALUES
('cot-1', 'col-1', '2026-01-01', 'ANNUALLY', 100000, 'Cotisation annuelle', 'ACTIVE'),
('cot-2', 'col-2', '2026-01-01', 'ANNUALLY', 100000, 'Cotisation annuelle', 'ACTIVE'),
('cot-3', 'col-3', '2026-01-01', 'ANNUALLY', 50000, 'Cotisation annuelle', 'ACTIVE');

INSERT INTO financial_account (id, collectivity_id, account_type, holder_name, amount, mobile_service, mobile_number) VALUES
('C1-A-CASH', 'col-1', 'CASH', NULL, 0, NULL, NULL),
('C1-A-MOBILE-1', 'col-1', 'MOBILE_BANKING', 'Mpanorina', 0, 'ORANGE_MONEY', '0370489612'),
('C2-A-CASH', 'col-2', 'CASH', NULL, 0, NULL, NULL),
('C2-A-MOBILE-1', 'col-2', 'MOBILE_BANKING', 'Dobo voalohany', 0, 'ORANGE_MONEY', '0320489612'),
('C3-A-CASH', 'col-3', 'CASH', NULL, 0, NULL, NULL);


INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-1-1', 'col-1', '2026-01-01', 100000, 'CASH', 'C1-A-CASH', 'C1-M1');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-1-1', 'C1-M1', 'cot-1', 100000, 'CASH', 'tx-1-1', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-1-2', 'col-1', '2026-01-01', 100000, 'CASH', 'C1-A-CASH', 'C1-M2');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-1-2', 'C1-M2', 'cot-1', 100000, 'CASH', 'tx-1-2', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-1-3', 'col-1', '2026-01-01', 100000, 'CASH', 'C1-A-CASH', 'C1-M3');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-1-3', 'C1-M3', 'cot-1', 100000, 'CASH', 'tx-1-3', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-1-4', 'col-1', '2026-01-01', 100000, 'CASH', 'C1-A-CASH', 'C1-M4');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-1-4', 'C1-M4', 'cot-1', 100000, 'CASH', 'tx-1-4', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-1-5', 'col-1', '2026-01-01', 100000, 'CASH', 'C1-A-CASH', 'C1-M5');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-1-5', 'C1-M5', 'cot-1', 100000, 'CASH', 'tx-1-5', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-1-6', 'col-1', '2026-01-01', 100000, 'CASH', 'C1-A-CASH', 'C1-M6');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-1-6', 'C1-M6', 'cot-1', 100000, 'CASH', 'tx-1-6', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-1-7', 'col-1', '2026-01-01', 60000, 'CASH', 'C1-A-CASH', 'C1-M7');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-1-7', 'C1-M7', 'cot-1', 60000, 'CASH', 'tx-1-7', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-1-8', 'col-1', '2026-01-01', 90000, 'CASH', 'C1-A-CASH', 'C1-M8');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-1-8', 'C1-M8', 'cot-1', 90000, 'CASH', 'tx-1-8', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-2-1', 'col-2', '2026-01-01', 60000, 'CASH', 'C2-A-CASH', 'C2-M1');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-2-1', 'C2-M1', 'cot-2', 60000, 'CASH', 'tx-2-1', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-2-2', 'col-2', '2026-01-01', 90000, 'CASH', 'C2-A-CASH', 'C2-M2');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-2-2', 'C2-M2', 'cot-2', 90000, 'CASH', 'tx-2-2', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-2-3', 'col-2', '2026-01-01', 100000, 'CASH', 'C2-A-CASH', 'C2-M3');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-2-3', 'C2-M3', 'cot-2', 100000, 'CASH', 'tx-2-3', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-2-4', 'col-2', '2026-01-01', 100000, 'CASH', 'C2-A-CASH', 'C2-M4');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-2-4', 'C2-M4', 'cot-2', 100000, 'CASH', 'tx-2-4', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-2-5', 'col-2', '2026-01-01', 100000, 'CASH', 'C2-A-CASH', 'C2-M5');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-2-5', 'C2-M5', 'cot-2', 100000, 'CASH', 'tx-2-5', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-2-6', 'col-2', '2026-01-01', 100000, 'CASH', 'C2-A-CASH', 'C2-M6');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-2-6', 'C2-M6', 'cot-2', 100000, 'CASH', 'tx-2-6', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-2-7', 'col-2', '2026-01-01', 40000, 'MOBILE_BANKING', 'C2-A-MOBILE-1', 'C2-M7');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-2-7', 'C2-M7', 'cot-2', 40000, 'MOBILE_BANKING', 'tx-2-7', '2026-01-01');

INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id)
VALUES ('tx-2-8', 'col-2', '2026-01-01', 60000, 'MOBILE_BANKING', 'C2-A-MOBILE-1', 'C2-M8');
INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date)
VALUES ('pmt-2-8', 'C2-M8', 'cot-2', 60000, 'MOBILE_BANKING', 'tx-2-8', '2026-01-01');

CREATE TABLE IF NOT EXISTS member_referee (
    member_id VARCHAR(36),
    referee_id VARCHAR(36),
    PRIMARY KEY (member_id, referee_id),
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (referee_id) REFERENCES member(id)
    );


INSERT INTO member_referee (member_id, referee_id) VALUES
('C1-M3', 'C1-M1'), ('C1-M3', 'C1-M2'),
('C1-M4', 'C1-M1'), ('C1-M4', 'C1-M2'),
('C1-M5', 'C1-M1'), ('C1-M5', 'C1-M2'),
('C1-M6', 'C1-M1'), ('C1-M6', 'C1-M2'),
('C1-M7', 'C1-M1'), ('C1-M7', 'C1-M2'),
('C1-M8', 'C1-M6'), ('C1-M8', 'C1-M7');

INSERT INTO member_referee (member_id, referee_id) VALUES
('C2-M3', 'C2-M1'), ('C2-M3', 'C2-M2'),
('C2-M4', 'C2-M1'), ('C2-M4', 'C2-M2'),
('C2-M5', 'C2-M1'), ('C2-M5', 'C2-M2'),
('C2-M6', 'C2-M1'), ('C2-M6', 'C2-M2'),
('C2-M7', 'C2-M1'), ('C2-M7', 'C2-M2'),
('C2-M8', 'C2-M6'), ('C2-M8', 'C2-M7');

INSERT INTO member_referee (member_id, referee_id) VALUES
('C3-M3', 'C3-M1'), ('C3-M3', 'C3-M2'),
('C3-M4', 'C3-M1'), ('C3-M4', 'C3-M2'),
('C3-M5', 'C3-M1'), ('C3-M5', 'C3-M2'),
('C3-M6', 'C3-M1'), ('C3-M6', 'C3-M2'),
('C3-M7', 'C3-M1'), ('C3-M7', 'C3-M2'),
('C3-M8', 'C3-M1'), ('C3-M8', 'C3-M2');

UPDATE financial_account SET amount = (
    SELECT COALESCE(SUM(amount), 0) FROM collectivity_transaction WHERE account_credited_id = 'C1-A-CASH'
) WHERE id = 'C1-A-CASH';

UPDATE financial_account SET amount = (
    SELECT COALESCE(SUM(amount), 0) FROM collectivity_transaction WHERE account_credited_id = 'C1-A-MOBILE-1'
) WHERE id = 'C1-A-MOBILE-1';

UPDATE financial_account SET amount = (
    SELECT COALESCE(SUM(amount), 0) FROM collectivity_transaction WHERE account_credited_id = 'C2-A-CASH'
) WHERE id = 'C2-A-CASH';

UPDATE financial_account SET amount = (
    SELECT COALESCE(SUM(amount), 0) FROM collectivity_transaction WHERE account_credited_id = 'C2-A-MOBILE-1'
) WHERE id = 'C2-A-MOBILE-1';
