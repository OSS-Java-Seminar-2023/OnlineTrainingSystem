CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 1. Bench Press
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Bench Press', 'Lay flat on a bench and push a weighted barbell upwards.', 'Barbell, Bench', 'Intermediate');

-- 2. Squats
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Squats', 'Stand with a barbell on your shoulders and squat down and up.', 'Barbell, Squat Rack', 'Intermediate');

-- 3. Deadlifts
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Deadlifts', 'Lift a weighted barbell from the ground to hip level.', 'Barbell, Weight Plates', 'Advanced');

-- 4. Pull-ups
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Pull-ups', 'Lift your body weight by pulling up on a bar.', 'Pull-up Bar', 'Intermediate');

-- 5. Dumbbell Shoulder Press
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Dumbbell Shoulder Press', 'Press dumbbells upwards from shoulder level.', 'Dumbbells, Bench', 'Intermediate');

-- 6. Bent-over Rows
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Bent-over Rows', 'Bend over and lift a barbell towards your abdomen.', 'Barbell, Weight Plates', 'Intermediate');

-- 7. Lunges
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Lunges', 'Step forward and lower your hips until both knees are bent at a 90-degree angle.', 'None (Bodyweight)', 'Beginner');

-- 8. Leg Press
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Leg Press', 'Push a weighted platform away from your body using your legs.', 'Leg Press Machine', 'Intermediate');

-- 9. Barbell Curls
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Barbell Curls', 'Lift a barbell upwards with your arms, focusing on bicep contraction.', 'Barbell, Weight Plates', 'Intermediate');

-- 10. Planks
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Planks', 'Hold a push-up position with your body straight as a plank.', 'None (Bodyweight)', 'Beginner');

-- 11. Tricep Dips
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Tricep Dips', 'Lower and raise your body using parallel bars.', 'Parallel Bars', 'Intermediate');

-- 12. Seated Cable Rows
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Seated Cable Rows', 'Pull the cable attachment towards your torso while seated.', 'Cable Machine, Bench', 'Intermediate');

-- 13. Romanian Deadlifts
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Romanian Deadlifts', 'Similar to deadlifts but focusing on the posterior chain.', 'Barbell, Weight Plates', 'Advanced');

-- 14. Hanging Leg Raises
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Hanging Leg Raises', 'Raise your legs while hanging from a bar.', 'Pull-up Bar', 'Intermediate');

-- 15. Bench Dips
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Bench Dips', 'Lower and raise your body using a bench and your arms.', 'Bench', 'Intermediate');

-- 16. Hammer Curls
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Hammer Curls', 'Curl dumbbells with a neutral grip.', 'Dumbbells', 'Intermediate');

-- 17. Reverse Lunges
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Reverse Lunges', 'Step backward and lower your hips until both knees are bent at a 90-degree angle.', 'None (Bodyweight)', 'Beginner');

-- 18. Decline Bench Press
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Decline Bench Press', 'Perform bench presses on a declined angle.', 'Barbell, Decline Bench', 'Intermediate');

-- 19. Calf Raises
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Calf Raises', 'Raise your heels using a platform to work your calf muscles.', 'Calf Raise Machine', 'Beginner');

-- 20. Russian Twists
INSERT INTO Exercise (id, name, description, equipment_needed, difficulty_level) VALUES
(uuid_generate_v4(), 'Russian Twists', 'Rotate your torso while holding a weight or without one.', 'Weight (Optional)', 'Intermediate');
