CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Insert data for coaches
INSERT INTO User_role (id, user_id, role_id)
SELECT uuid_generate_v4(), user_id, (SELECT id FROM Role WHERE name = 'COACH') FROM Coach;

-- Insert data for clients
INSERT INTO User_role (id, user_id, role_id)
SELECT uuid_generate_v4(), user_id, (SELECT id FROM Role WHERE name = 'CLIENT') FROM Client;
