INSERT INTO wallet (id, balance, blocked_balance) VALUES
  ('708835db-5e8d-49df-b57a-60b912aca11b', 500000, 10000);

INSERT INTO CARD (pan, name, expiration_date, balance, wallet_id) VALUES
  ('0000000000000000', 'Monkey D. Luffy', '2020/12', 0, '708835db-5e8d-49df-b57a-60b912aca11b');

INSERT INTO CARD (pan, name, expiration_date, balance, wallet_id) VALUES
  ('0000000000000001', 'Roronoa Zoro', '2020/11', 0, '708835db-5e8d-49df-b57a-60b912aca11b');


