-- 1. Nettoyage (ordre correct)
DELETE FROM activity_attendance;
DELETE FROM collectivity_activity;
DELETE FROM member_payment;
DELETE FROM collectivity_transaction;
DELETE FROM membership_fee;
DELETE FROM mandate;
DELETE FROM membership;
DELETE FROM financial_account;
DELETE FROM member_referee;
DELETE FROM member;
DELETE FROM collectivity;

-- 2. Collectivités (tableau 1)
INSERT INTO collectivity (id, location, federation_approval, unique_number, unique_name) VALUES
('col-1', 'Ambatondrazaka', true, '1', 'Mpanorina'),
('col-2', 'Ambatondrazaka', true, '2', 'Dobo voalohany'),
('col-3', 'Brickaville', true, '3', 'Tantely mamy');

-- 3. Membres anciens (tableaux 2,3,4) – 24 membres, date d'adhésion à la fédération au 2025-01-01 (>6 mois)
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, federation_joining_date) VALUES
('C1-M1', 'Prénom membre 1', 'Nom membre 1', '1980-02-01', 'MALE', 'Lot II V M Ambato.', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', 'PRESIDENT', '2025-01-01'),
('C1-M2', 'Prénom membre 2', 'Nom membre 2', '1982-03-05', 'MALE', 'Lot II F Ambato.', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', 'VICE_PRESIDENT', '2025-01-01'),
('C1-M3', 'Prénom membre 3', 'Nom membre 3', '1992-03-10', 'MALE', 'Lot II J Ambato.', 'Collecteur', '0331234567', 'member.3@fed-agri.mg', 'SECRETARY', '2025-01-01'),
('C1-M4', 'Prénom membre 4', 'Nom membre 4', '1988-05-22', 'FEMALE', 'Lot A K 50 Ambato.', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', 'TREASURER', '2025-01-01'),
('C1-M5', 'Prénom membre 5', 'Nom membre 5', '1999-08-21', 'MALE', 'Lot UV 80 Ambato.', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C1-M6', 'Prénom membre 6', 'Nom membre 6', '1998-08-22', 'FEMALE', 'Lot UV 6 Ambato.', 'Riziculteur', '0372234567', 'member.6@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C1-M7', 'Prénom membre 7', 'Nom membre 7', '1998-01-31', 'MALE', 'Lot UV 7 Ambato.', 'Riziculteur', '0374234567', 'member.7@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C1-M8', 'Prénom membre 8', 'Nom membre 8', '1975-08-20', 'MALE', 'Lot UV 8 Ambato.', 'Riziculteur', '0370234567', 'member.8@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C2-M1', 'Prénom membre 1', 'Nom membre 1', '1980-02-01', 'MALE', 'Lot II V M Ambato.', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C2-M2', 'Prénom membre 2', 'Nom membre 2', '1982-03-05', 'MALE', 'Lot II F Ambato.', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C2-M3', 'Prénom membre 3', 'Nom membre 3', '1992-03-10', 'MALE', 'Lot II J Ambato.', 'Collecteur', '0331234567', 'member.3@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C2-M4', 'Prénom membre 4', 'Nom membre 4', '1988-05-22', 'FEMALE', 'Lot A K 50 Ambato.', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C2-M5', 'Prénom membre 5', 'Nom membre 5', '1999-08-21', 'MALE', 'Lot UV 80 Ambato.', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', 'PRESIDENT', '2025-01-01'),
('C2-M6', 'Prénom membre 6', 'Nom membre 6', '1998-08-22', 'FEMALE', 'Lot UV 6 Ambato.', 'Riziculteur', '0372234567', 'member.6@fed-agri.mg', 'VICE_PRESIDENT', '2025-01-01'),
('C2-M7', 'Prénom membre 7', 'Nom membre 7', '1998-01-31', 'MALE', 'Lot UV 7 Ambato.', 'Riziculteur', '0374234567', 'member.7@fed-agri.mg', 'SECRETARY', '2025-01-01'),
('C2-M8', 'Prénom membre 8', 'Nom membre 8', '1975-08-20', 'MALE', 'Lot UV 8 Ambato.', 'Riziculteur', '0370234567', 'member.8@fed-agri.mg', 'TREASURER', '2025-01-01'),
('C3-M1', 'Prénom membre 9', 'Nom membre 9', '1988-01-02', 'MALE', 'Lot 3 J Antisibare', 'Apiculteur', '034034567', 'member.9@fed-agri.mg', 'PRESIDENT', '2025-01-01'),
('C3-M2', 'Prénom membre 10', 'Nom membre 10', '1982-03-05', 'MALE', 'Lot 2 J Antisibare', 'Agriculteur', '0338634567', 'member.10@fed-agri.mg', 'VICE_PRESIDENT', '2025-01-01'),
('C3-M3', 'Prénom membre 11', 'Nom membre 11', '1992-03-12', 'MALE', 'Lot 8 KM Antisibare', 'Collecteur', '0338234567', 'member.11@fed-agri.mg', 'SECRETARY', '2025-01-01'),
('C3-M4', 'Prénom membre 12', 'Nom membre 12', '1988-05-10', 'FEMALE', 'Lot 5 K 50 Antisibare', 'Distributeur', '0382334567', 'member.12@fed-agri.mg', 'TREASURER', '2025-01-01'),
('C3-M5', 'Prénom membre 13', 'Nom membre 13', '1999-08-11', 'MALE', 'Lot UV 80 Antisibare', 'Apiculteur', '0373365567', 'member.13@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C3-M6', 'Prénom membre 14', 'Nom membre 14', '1998-08-09', 'FEMALE', 'Lot UV 6 Antisibare', 'Apiculteur', '0378234567', 'member.14@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C3-M7', 'Prénom membre 15', 'Nom membre 15', '1998-01-13', 'MALE', 'Lot UV 7 Antisibare', 'Apiculteur', '0374914567', 'member.15@fed-agri.mg', 'SENIOR', '2025-01-01'),
('C3-M8', 'Prénom membre 16', 'Nom membre 16', '1975-08-02', 'MALE', 'Lot UV 8 Antisibare', 'Apiculteur', '0370634567', 'member.16@fed-agri.mg', 'SENIOR', '2025-01-01');

-- 4. Appartenances (membership) – début au 01/01/2026 pour tous
INSERT INTO membership (id, member_id, collectivity_id, start_date, end_date) VALUES
('ms-c1-1', 'C1-M1', 'col-1', '2026-01-01', NULL),
('ms-c1-2', 'C1-M2', 'col-1', '2026-01-01', NULL),
('ms-c1-3', 'C1-M3', 'col-1', '2026-01-01', NULL),
('ms-c1-4', 'C1-M4', 'col-1', '2026-01-01', NULL),
('ms-c1-5', 'C1-M5', 'col-1', '2026-01-01', NULL),
('ms-c1-6', 'C1-M6', 'col-1', '2026-01-01', NULL),
('ms-c1-7', 'C1-M7', 'col-1', '2026-01-01', NULL),
('ms-c1-8', 'C1-M8', 'col-1', '2026-01-01', NULL),
('ms-c2-1', 'C2-M1', 'col-2', '2026-01-01', NULL),
('ms-c2-2', 'C2-M2', 'col-2', '2026-01-01', NULL),
('ms-c2-3', 'C2-M3', 'col-2', '2026-01-01', NULL),
('ms-c2-4', 'C2-M4', 'col-2', '2026-01-01', NULL),
('ms-c2-5', 'C2-M5', 'col-2', '2026-01-01', NULL),
('ms-c2-6', 'C2-M6', 'col-2', '2026-01-01', NULL),
('ms-c2-7', 'C2-M7', 'col-2', '2026-01-01', NULL),
('ms-c2-8', 'C2-M8', 'col-2', '2026-01-01', NULL),
('ms-c3-1', 'C3-M1', 'col-3', '2026-01-01', NULL),
('ms-c3-2', 'C3-M2', 'col-3', '2026-01-01', NULL),
('ms-c3-3', 'C3-M3', 'col-3', '2026-01-01', NULL),
('ms-c3-4', 'C3-M4', 'col-3', '2026-01-01', NULL),
('ms-c3-5', 'C3-M5', 'col-3', '2026-01-01', NULL),
('ms-c3-6', 'C3-M6', 'col-3', '2026-01-01', NULL),
('ms-c3-7', 'C3-M7', 'col-3', '2026-01-01', NULL),
('ms-c3-8', 'C3-M8', 'col-3', '2026-01-01', NULL);

-- 5. Mandats (présidents, etc.) – début au 01/01/2026, fin 31/12/2026
INSERT INTO mandate (id, member_id, collectivity_id, role, start_date, end_date) VALUES
('md-1-1', 'C1-M1', 'col-1', 'PRESIDENT', '2026-01-01', '2026-12-31'),
('md-1-2', 'C1-M2', 'col-1', 'VICE_PRESIDENT', '2026-01-01', '2026-12-31'),
('md-1-3', 'C1-M3', 'col-1', 'SECRETARY', '2026-01-01', '2026-12-31'),
('md-1-4', 'C1-M4', 'col-1', 'TREASURER', '2026-01-01', '2026-12-31'),
('md-2-1', 'C2-M5', 'col-2', 'PRESIDENT', '2026-01-01', '2026-12-31'),
('md-2-2', 'C2-M6', 'col-2', 'VICE_PRESIDENT', '2026-01-01', '2026-12-31'),
('md-2-3', 'C2-M7', 'col-2', 'SECRETARY', '2026-01-01', '2026-12-31'),
('md-2-4', 'C2-M8', 'col-2', 'TREASURER', '2026-01-01', '2026-12-31'),
('md-3-1', 'C3-M1', 'col-3', 'PRESIDENT', '2026-01-01', '2026-12-31'),
('md-3-2', 'C3-M2', 'col-3', 'VICE_PRESIDENT', '2026-01-01', '2026-12-31'),
('md-3-3', 'C3-M3', 'col-3', 'SECRETARY', '2026-01-01', '2026-12-31'),
('md-3-4', 'C3-M4', 'col-3', 'TREASURER', '2026-01-01', '2026-12-31');

-- 6. Comptes financiers initiaux (tableau 1 de la nouvelle consigne)
INSERT INTO financial_account (id, collectivity_id, account_type, holder_name, amount, mobile_service, mobile_number) VALUES
('C1-A-CASH', 'col-1', 'CASH', NULL, 0, NULL, NULL),
('C1-A-MOBILE-1', 'col-1', 'MOBILE_BANKING', 'Manacor', 0, 'ORANGE_MONEY', '03754896112'),
('C2-A-CASH', 'col-2', 'CASH', NULL, 0, NULL, NULL),
('C2-A-MOBILE-1', 'col-2', 'MOBILE_BANKING', 'Dobo vokohary', 0, 'ORANGE_MONEY', '03240586112'),
('C3-A-CASH', 'col-3', 'CASH', NULL, 0, NULL, NULL);

-- 7. Nouveaux comptes pour collectivité 3 (2 bancaires, 1 mobile money)
INSERT INTO financial_account (id, collectivity_id, account_type, holder_name, amount, bank_name, bank_code, branch_code, account_number, rib_key) VALUES
('C3-A-BANK-1', 'col-3', 'BANK', 'Koto', 0, 'BMOI', '00004', '00001', '1234567890', '12'),
('C3-A-BANK-2', 'col-3', 'BANK', 'Naivo', 0, 'BRED', '00008', '00003', '4567890123', '58');

INSERT INTO financial_account (id, collectivity_id, account_type, holder_name, amount, mobile_service, mobile_number) VALUES
('C3-A-MOBILE-1', 'col-3', 'MOBILE_BANKING', 'Kolo', 0, 'MVOLA', '0341889612');

-- 8. Nouvelles cotisations (tableaux 12,13,14)
INSERT INTO membership_fee (id, collectivity_id, eligible_from, frequency, amount, label, status) VALUES
('cot-1', 'col-1', '2026-01-01', 'ANNUALLY', 200000, 'Cotisation annuelle', 'ACTIVE'),
('cot-2', 'col-1', '2026-04-30', 'PUNCTUALLY', 20000, 'Famangiana', 'ACTIVE'),
('cot-3', 'col-2', '2026-01-01', 'ANNUALLY', 200000, 'Cotisation annuelle', 'ACTIVE'),
('cot-4', 'col-2', '2025-01-01', 'ANNUALLY', 100000, 'Cotisation 2025', 'INACTIVE'),
('cot-5', 'col-3', '2026-04-01', 'MONTHLY', 25000, 'Cotisation mensuelle', 'ACTIVE');

-- 9. Paiements et transactions pour collectivité 1 (tableau 15)
-- Chaque paiement génère une transaction (collectivity_transaction) et un enregistrement dans member_payment.
-- Les identifiants sont libres, on utilise des préfixes "tx1-" et "pay1-".
INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id) VALUES
('tx1-1', 'col-1', '2026-01-01', 200000, 'CASH', 'C1-A-CASH', 'C1-M1'),
('tx1-2', 'col-1', '2026-01-01', 200000, 'CASH', 'C1-A-CASH', 'C1-M2'),
('tx1-3', 'col-1', '2026-01-01', 200000, 'MOBILE_BANKING', 'C1-A-MOBILE-1', 'C1-M3'),
('tx1-4', 'col-1', '2026-01-01', 200000, 'MOBILE_BANKING', 'C1-A-MOBILE-1', 'C1-M4'),
('tx1-5', 'col-1', '2026-01-01', 150000, 'MOBILE_BANKING', 'C1-A-MOBILE-1', 'C1-M5'),
('tx1-6', 'col-1', '2026-05-01', 100000, 'CASH', 'C1-A-CASH', 'C1-M6'),
('tx1-7', 'col-1', '2026-05-01',  60000, 'CASH', 'C1-A-CASH', 'C1-M7'),
('tx1-8', 'col-1', '2026-05-01',  90000, 'CASH', 'C1-A-CASH', 'C1-M8');

INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date) VALUES
('pay1-1', 'C1-M1', 'cot-1', 200000, 'CASH', 'tx1-1', '2026-01-01'),
('pay1-2', 'C1-M2', 'cot-1', 200000, 'CASH', 'tx1-2', '2026-01-01'),
('pay1-3', 'C1-M3', 'cot-1', 200000, 'MOBILE_BANKING', 'tx1-3', '2026-01-01'),
('pay1-4', 'C1-M4', 'cot-1', 200000, 'MOBILE_BANKING', 'tx1-4', '2026-01-01'),
('pay1-5', 'C1-M5', 'cot-1', 150000, 'MOBILE_BANKING', 'tx1-5', '2026-01-01'),
('pay1-6', 'C1-M6', 'cot-1', 100000, 'CASH', 'tx1-6', '2026-05-01'),
('pay1-7', 'C1-M7', 'cot-1',  60000, 'CASH', 'tx1-7', '2026-05-01'),
('pay1-8', 'C1-M8', 'cot-1',  90000, 'CASH', 'tx1-8', '2026-05-01');

-- 10. Paiements et transactions pour collectivité 2 (tableau 16)
-- Les membres débiteurs sont ceux de col-2 (C2-M1 etc.)
INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id) VALUES
('tx2-1', 'col-2', '2026-01-01', 120000, 'CASH', 'C2-A-CASH', 'C2-M1'),
('tx2-2', 'col-2', '2026-01-01', 180000, 'CASH', 'C2-A-CASH', 'C2-M2'),
('tx2-3', 'col-2', '2026-01-01', 200000, 'CASH', 'C2-A-CASH', 'C2-M3'),
('tx2-4', 'col-2', '2026-01-01', 200000, 'CASH', 'C2-A-CASH', 'C2-M4'),
('tx2-5', 'col-2', '2026-01-01', 200000, 'CASH', 'C2-A-CASH', 'C2-M5'),
('tx2-6', 'col-2', '2026-01-01', 200000, 'CASH', 'C2-A-CASH', 'C2-M6'),
('tx2-7', 'col-2', '2026-01-01',  80000, 'MOBILE_BANKING', 'C2-A-MOBILE-1', 'C2-M7'),
('tx2-8', 'col-2', '2026-01-01', 120000, 'MOBILE_BANKING', 'C2-A-MOBILE-1', 'C2-M8');

INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date) VALUES
('pay2-1', 'C2-M1', 'cot-3', 120000, 'CASH', 'tx2-1', '2026-01-01'),
('pay2-2', 'C2-M2', 'cot-3', 180000, 'CASH', 'tx2-2', '2026-01-01'),
('pay2-3', 'C2-M3', 'cot-3', 200000, 'CASH', 'tx2-3', '2026-01-01'),
('pay2-4', 'C2-M4', 'cot-3', 200000, 'CASH', 'tx2-4', '2026-01-01'),
('pay2-5', 'C2-M5', 'cot-3', 200000, 'CASH', 'tx2-5', '2026-01-01'),
('pay2-6', 'C2-M6', 'cot-3', 200000, 'CASH', 'tx2-6', '2026-01-01'),
('pay2-7', 'C2-M7', 'cot-3',  80000, 'MOBILE_BANKING', 'tx2-7', '2026-01-01'),
('pay2-8', 'C2-M8', 'cot-3', 120000, 'MOBILE_BANKING', 'tx2-8', '2026-01-01');

-- 11. Paiements et transactions pour collectivité 3 (données fournies en dernier)
-- Les identifiants des transactions/paiements sont préfixés "tx3-" et "pay3-"
INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id) VALUES
-- Avril 2026
('tx3-a1', 'col-3', '2026-04-01', 25000, 'BANK_TRANSFER', 'C3-A-BANK-1', 'C3-M1'),
('tx3-a2', 'col-3', '2026-04-01', 25000, 'BANK_TRANSFER', 'C3-A-BANK-1', 'C3-M2'),
('tx3-a3', 'col-3', '2026-04-01', 25000, 'BANK_TRANSFER', 'C3-A-BANK-1', 'C3-M3'),
('tx3-a4', 'col-3', '2026-04-01', 25000, 'BANK_TRANSFER', 'C3-A-BANK-1', 'C3-M4'),
('tx3-a5', 'col-3', '2026-04-01', 25000, 'BANK_TRANSFER', 'C3-A-BANK-2', 'C3-M5'),
('tx3-a6', 'col-3', '2026-04-01', 25000, 'BANK_TRANSFER', 'C3-A-BANK-2', 'C3-M6'),
('tx3-a7', 'col-3', '2026-04-01', 25000, 'CASH', 'C3-A-CASH', 'C3-M7'),
('tx3-a8', 'col-3', '2026-04-01', 25000, 'CASH', 'C3-A-CASH', 'C3-M8'),
-- Mai 2026
('tx3-b1', 'col-3', '2026-05-01', 25000, 'BANK_TRANSFER', 'C3-A-BANK-1', 'C3-M1'),
('tx3-b2', 'col-3', '2026-05-01', 25000, 'BANK_TRANSFER', 'C3-A-BANK-1', 'C3-M2'),
('tx3-b3', 'col-3', '2026-05-01', 15000, 'BANK_TRANSFER', 'C3-A-MOBILE-1', 'C3-M3'),
('tx3-b4', 'col-3', '2026-05-01', 15000, 'BANK_TRANSFER', 'C3-A-MOBILE-1', 'C3-M4'),
('tx3-b5', 'col-3', '2026-05-01', 20000, 'BANK_TRANSFER', 'C3-A-BANK-2', 'C3-M5'),
('tx3-b6', 'col-3', '2026-05-01', 25000, 'BANK_TRANSFER', 'C3-A-BANK-2', 'C3-M6'),
('tx3-b7', 'col-3', '2026-05-01',  5000, 'CASH', 'C3-A-CASH', 'C3-M7'),
('tx3-b8', 'col-3', '2026-05-01',  5000, 'CASH', 'C3-A-CASH', 'C3-M8');

INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date) VALUES
-- Avril
('pay3-a1', 'C3-M1', 'cot-5', 25000, 'BANK_TRANSFER', 'tx3-a1', '2026-04-01'),
('pay3-a2', 'C3-M2', 'cot-5', 25000, 'BANK_TRANSFER', 'tx3-a2', '2026-04-01'),
('pay3-a3', 'C3-M3', 'cot-5', 25000, 'BANK_TRANSFER', 'tx3-a3', '2026-04-01'),
('pay3-a4', 'C3-M4', 'cot-5', 25000, 'BANK_TRANSFER', 'tx3-a4', '2026-04-01'),
('pay3-a5', 'C3-M5', 'cot-5', 25000, 'BANK_TRANSFER', 'tx3-a5', '2026-04-01'),
('pay3-a6', 'C3-M6', 'cot-5', 25000, 'BANK_TRANSFER', 'tx3-a6', '2026-04-01'),
('pay3-a7', 'C3-M7', 'cot-5', 25000, 'CASH', 'tx3-a7', '2026-04-01'),
('pay3-a8', 'C3-M8', 'cot-5', 25000, 'CASH', 'tx3-a8', '2026-04-01'),
-- Mai
('pay3-b1', 'C3-M1', 'cot-5', 25000, 'BANK_TRANSFER', 'tx3-b1', '2026-05-01'),
('pay3-b2', 'C3-M2', 'cot-5', 25000, 'BANK_TRANSFER', 'tx3-b2', '2026-05-01'),
('pay3-b3', 'C3-M3', 'cot-5', 15000, 'BANK_TRANSFER', 'tx3-b3', '2026-05-01'),
('pay3-b4', 'C3-M4', 'cot-5', 15000, 'BANK_TRANSFER', 'tx3-b4', '2026-05-01'),
('pay3-b5', 'C3-M5', 'cot-5', 20000, 'BANK_TRANSFER', 'tx3-b5', '2026-05-01'),
('pay3-b6', 'C3-M6', 'cot-5', 25000, 'BANK_TRANSFER', 'tx3-b6', '2026-05-01'),
('pay3-b7', 'C3-M7', 'cot-5',  5000, 'CASH', 'tx3-b7', '2026-05-01'),
('pay3-b8', 'C3-M8', 'cot-5',  5000, 'CASH', 'tx3-b8', '2026-05-01');

-- 12. Nouveaux membres (tableaux <random> – valeurs génériques uniques)
-- Collectivité 1 : 4 nouveaux membres juniors
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, federation_joining_date) VALUES
('new1-1', 'Nouveau1', 'Nom1', '2000-01-01', 'MALE', 'Adr1', 'Prof1', '0310000001', 'new1@mail.com', 'JUNIOR', '2026-04-01'),
('new1-2', 'Nouveau2', 'Nom2', '2000-01-02', 'FEMALE', 'Adr2', 'Prof2', '0310000002', 'new2@mail.com', 'JUNIOR', '2026-04-01'),
('new1-3', 'Nouveau3', 'Nom3', '2000-01-03', 'MALE', 'Adr3', 'Prof3', '0310000003', 'new3@mail.com', 'JUNIOR', '2026-05-01'),
('new1-4', 'Nouveau4', 'Nom4', '2000-01-04', 'FEMALE', 'Adr4', 'Prof4', '0310000004', 'new4@mail.com', 'JUNIOR', '2026-06-01');

-- Appartenances pour collectivité 1
INSERT INTO membership (id, member_id, collectivity_id, start_date, end_date) VALUES
('ms-new1-1', 'new1-1', 'col-1', '2026-04-01', NULL),
('ms-new1-2', 'new1-2', 'col-1', '2026-04-01', NULL),
('ms-new1-3', 'new1-3', 'col-1', '2026-05-01', NULL),
('ms-new1-4', 'new1-4', 'col-1', '2026-06-01', NULL);

-- Référents pour ces nouveaux membres (C1-M1 et C1-M2)
INSERT INTO member_referee (member_id, referee_id) VALUES
('new1-1', 'C1-M1'), ('new1-1', 'C1-M2'),
('new1-2', 'C1-M1'), ('new1-2', 'C1-M2'),
('new1-3', 'C1-M1'), ('new1-3', 'C1-M2'),
('new1-4', 'C1-M1'), ('new1-4', 'C1-M2');

-- Collectivité 2 : 3 nouveaux membres juniors
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, federation_joining_date) VALUES
('new2-1', 'Nouveau2-1', 'Nom2-1', '1999-01-01', 'MALE', 'Adr21', 'Prof21', '0320000011', 'new21@mail.com', 'JUNIOR', '2026-03-01'),
('new2-2', 'Nouveau2-2', 'Nom2-2', '1999-01-02', 'FEMALE', 'Adr22', 'Prof22', '0320000012', 'new22@mail.com', 'JUNIOR', '2026-03-01'),
('new2-3', 'Nouveau2-3', 'Nom2-3', '1999-01-03', 'MALE', 'Adr23', 'Prof23', '0320000013', 'new23@mail.com', 'JUNIOR', '2026-03-01');

INSERT INTO membership (id, member_id, collectivity_id, start_date, end_date) VALUES
('ms-new2-1', 'new2-1', 'col-2', '2026-03-01', NULL),
('ms-new2-2', 'new2-2', 'col-2', '2026-03-01', NULL),
('ms-new2-3', 'new2-3', 'col-2', '2026-03-01', NULL);

INSERT INTO member_referee (member_id, referee_id) VALUES
('new2-1', 'C1-M1'), ('new2-1', 'C1-M2'),
('new2-2', 'C1-M1'), ('new2-2', 'C1-M2'),
('new2-3', 'C1-M1'), ('new2-3', 'C1-M2');

-- Collectivité 3 : 6 nouveaux membres juniors
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, federation_joining_date) VALUES
('new3-1', 'Nouveau3-1', 'Nom3-1', '1998-01-01', 'MALE', 'Adr31', 'Prof31', '0330000101', 'new31@mail.com', 'JUNIOR', '2026-01-01'),
('new3-2', 'Nouveau3-2', 'Nom3-2', '1998-01-02', 'FEMALE', 'Adr32', 'Prof32', '0330000102', 'new32@mail.com', 'JUNIOR', '2026-02-01'),
('new3-3', 'Nouveau3-3', 'Nom3-3', '1998-01-03', 'MALE', 'Adr33', 'Prof33', '0330000103', 'new33@mail.com', 'JUNIOR', '2026-02-01'),
('new3-4', 'Nouveau3-4', 'Nom3-4', '1998-01-04', 'FEMALE', 'Adr34', 'Prof34', '0330000104', 'new34@mail.com', 'JUNIOR', '2026-03-01'),
('new3-5', 'Nouveau3-5', 'Nom3-5', '1998-01-05', 'MALE', 'Adr35', 'Prof35', '0330000105', 'new35@mail.com', 'JUNIOR', '2026-03-01'),
('new3-6', 'Nouveau3-6', 'Nom3-6', '1998-01-06', 'FEMALE', 'Adr36', 'Prof36', '0330000106', 'new36@mail.com', 'JUNIOR', '2026-03-01');

INSERT INTO membership (id, member_id, collectivity_id, start_date, end_date) VALUES
('ms-new3-1', 'new3-1', 'col-3', '2026-01-01', NULL),
('ms-new3-2', 'new3-2', 'col-3', '2026-02-01', NULL),
('ms-new3-3', 'new3-3', 'col-3', '2026-02-01', NULL),
('ms-new3-4', 'new3-4', 'col-3', '2026-03-01', NULL),
('ms-new3-5', 'new3-5', 'col-3', '2026-03-01', NULL),
('ms-new3-6', 'new3-6', 'col-3', '2026-03-01', NULL);

INSERT INTO member_referee (member_id, referee_id) VALUES
('new3-1', 'C3-M1'), ('new3-1', 'C3-M2'),
('new3-2', 'C3-M1'), ('new3-2', 'C3-M2'),
('new3-3', 'C3-M1'), ('new3-3', 'C3-M2'),
('new3-4', 'C3-M1'), ('new3-4', 'C3-M2'),
('new3-5', 'C3-M1'), ('new3-5', 'C3-M2'),
('new3-6', 'C3-M1'), ('new3-6', 'C3-M2');

-- 13. Mise à jour des soldes des comptes financiers (somme des transactions par compte)
UPDATE financial_account SET amount = (SELECT COALESCE(SUM(amount),0) FROM collectivity_transaction WHERE account_credited_id = 'C1-A-CASH') WHERE id = 'C1-A-CASH';
UPDATE financial_account SET amount = (SELECT COALESCE(SUM(amount),0) FROM collectivity_transaction WHERE account_credited_id = 'C1-A-MOBILE-1') WHERE id = 'C1-A-MOBILE-1';
UPDATE financial_account SET amount = (SELECT COALESCE(SUM(amount),0) FROM collectivity_transaction WHERE account_credited_id = 'C2-A-CASH') WHERE id = 'C2-A-CASH';
UPDATE financial_account SET amount = (SELECT COALESCE(SUM(amount),0) FROM collectivity_transaction WHERE account_credited_id = 'C2-A-MOBILE-1') WHERE id = 'C2-A-MOBILE-1';
UPDATE financial_account SET amount = (SELECT COALESCE(SUM(amount),0) FROM collectivity_transaction WHERE account_credited_id = 'C3-A-CASH') WHERE id = 'C3-A-CASH';
UPDATE financial_account SET amount = (SELECT COALESCE(SUM(amount),0) FROM collectivity_transaction WHERE account_credited_id = 'C3-A-BANK-1') WHERE id = 'C3-A-BANK-1';
UPDATE financial_account SET amount = (SELECT COALESCE(SUM(amount),0) FROM collectivity_transaction WHERE account_credited_id = 'C3-A-BANK-2') WHERE id = 'C3-A-BANK-2';
UPDATE financial_account SET amount = (SELECT COALESCE(SUM(amount),0) FROM collectivity_transaction WHERE account_credited_id = 'C3-A-MOBILE-1') WHERE id = 'C3-A-MOBILE-1';