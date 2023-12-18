CREATE TABLE User_table (
    id UUID PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(30) NOT NULL,
    country VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INTEGER NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE Role (
    id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE User_role (
    user_id UUID REFERENCES User_table(id),
    role_id serial REFERENCES Role(id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE Coach (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES User_table(id),
    years_of_experience NUMERIC(5,2) NOT NULL,
    education TEXT NOT NULL,
    monthly_price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE Client (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES User_table(id),
    medical_condition TEXT,
    injuries TEXT
);

CREATE TABLE Contract (
    id UUID PRIMARY KEY,
    coach_id UUID REFERENCES Coach(id),
    client_id UUID REFERENCES Client(id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    monthly_price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE Measurement (
    id UUID PRIMARY KEY,
    contract_id UUID REFERENCES Contract(id),
    measurement_date DATE NOT NULL,
    body_weight NUMERIC(6, 2) NOT NULL,
    body_fat NUMERIC(5, 2) NOT NULL,
    waist_circumference NUMERIC(6, 2) NOT NULL,
    chest_circumference NUMERIC(6, 2) NOT NULL,
    arm_circumference NUMERIC(6, 2) NOT NULL,
    leg_circumference NUMERIC(6, 2) NOT NULL
);

CREATE TABLE Workout (
    id UUID PRIMARY KEY,
    contract_id UUID REFERENCES Contract(id),
    dateOfWorkout DATE,
    number_of_exercises INTEGER NOT NULL,
    warming_up_time_in_seconds INTEGER NOT NULL,
    number_of_sets INTEGER NOT NULL,
    pause_between_sets_in_seconds INTEGER NOT NULL,
    self_rating INTEGER
);


CREATE TABLE Exercise (
    id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description TEXT NOT NULL,
    equipment_needed TEXT NOT NULL,
    difficulty_level VARCHAR(30) NOT NULL
);

CREATE TABLE WorkoutSession (
    id UUID PRIMARY KEY,
    workout_id UUID REFERENCES Workout(id),
    exercise_id UUID,
    number_of_reps INTEGER NOT NULL,
    pause_after_exercise_in_seconds INTEGER NOT NULL,
    weight NUMERIC(6, 2) NOT NULL,
    CONSTRAINT fk_exercise
    FOREIGN KEY (exercise_id)
    REFERENCES Exercise(id)
    ON DELETE SET NULL
);
