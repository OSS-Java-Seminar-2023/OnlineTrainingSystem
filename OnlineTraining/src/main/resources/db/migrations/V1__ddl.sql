CREATE TABLE "User" (
    id VARCHAR(50) PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL,
    address TEXT NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INTEGER NOT NULL,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE Coach (
    id VARCHAR(50) PRIMARY KEY,
    user_id VARCHAR(50) REFERENCES "User"(id),
    years_of_experience INTEGER NOT NULL,
    education TEXT NOT NULL,
    monthly_price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE Client (
    id VARCHAR(50) PRIMARY KEY,
    user_id VARCHAR(50) REFERENCES "User"(id),
    medical_condition TEXT,
    injuries TEXT
);

CREATE TABLE Measurement (
    id VARCHAR(50) PRIMARY KEY,
    body_weight NUMERIC(6, 2) NOT NULL,
    body_fat NUMERIC(5, 2) NOT NULL,
    waist_circumference NUMERIC(5, 2) NOT NULL,
    chest_circumference NUMERIC(5, 2) NOT NULL,
    arm_circumference NUMERIC(5, 2) NOT NULL,
    leg_circumference NUMERIC(5, 2) NOT NULL
);

CREATE TABLE Contract (
    id VARCHAR(50) PRIMARY KEY,
    coach_id VARCHAR(50) REFERENCES Coach(id),
    client_id VARCHAR(50) REFERENCES Client(id),
    starting_measurement_id VARCHAR(50) REFERENCES Measurement(id),
    goal_measurement_id VARCHAR(50) REFERENCES Measurement(id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    monthly_price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE Workout (
    id VARCHAR(50) PRIMARY KEY,
    contract_id VARCHAR(50) REFERENCES Contract(id),
    number_of_exercises INTEGER NOT NULL,
    warming_up_time_in_seconds INTEGER NOT NULL,
    number_of_sets INTEGER NOT NULL,
    pause_between_sets_in_seconds INTEGER NOT NULL,
    self_rating INTEGER
);

CREATE TABLE Exercise (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description TEXT NOT NULL,
    equipment_needed TEXT NOT NULL,
    difficulty_level VARCHAR(30) NOT NULL
);

CREATE TABLE WorkoutSession (
    id VARCHAR(50) PRIMARY KEY,
    workout_id VARCHAR(50) REFERENCES Workout(id),
    exercise_id VARCHAR(50) REFERENCES Exercise(id),
    number_of_reps INTEGER NOT NULL,
    pause_after_exercise_in_seconds INTEGER NOT NULL,
    weight NUMERIC(6, 2) NOT NULL
);