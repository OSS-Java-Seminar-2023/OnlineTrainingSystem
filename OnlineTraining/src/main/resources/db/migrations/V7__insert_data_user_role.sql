-- Insert data for coaches
INSERT INTO User_role ( user_id, role_id)
SELECT user_id, (SELECT id FROM Role WHERE name = 'COACH') FROM Coach;

-- Insert data for clients
INSERT INTO User_role (user_id, role_id)
SELECT user_id, (SELECT id FROM Role WHERE name = 'CLIENT') FROM Client;
