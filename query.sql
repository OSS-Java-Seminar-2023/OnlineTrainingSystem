
CREATE TYPE gender_enum AS ENUM ('male', 'female');
CREATE TYPE difficulty_enum AS ENUM ('very easy', 'easy', 'moderate', 'hard', 'very hard');


CREATE TABLE Users (
    user_id serial PRIMARY KEY,
    first_name varchar(30),
    last_name varchar(30),
    email varchar(30),
    address text,
    phone_number varchar(20),
    gender gender_enum,
    age integer
);


CREATE TABLE Coach (
    coach_id serial PRIMARY KEY,
    user_id integer REFERENCES Users(user_id),
    years_of_experience integer,
    education text,
    monthly_price numeric(10, 2)
);


CREATE TABLE Client (
    client_id serial PRIMARY KEY,
    user_id integer REFERENCES Users(user_id),
    medical_condition text,
    injuries text
);


CREATE TABLE Measurement (
    measurement_id serial PRIMARY KEY,
    body_weight numeric(6, 2),
    body_fat numeric(5, 2),
    waist_circumference numeric(5, 2),
    chest_circumference numeric(5, 2),
    arm_circumference numeric(5, 2),
    leg_circumference numeric(5, 2)
);

CREATE TABLE Contract (
    contract_id serial PRIMARY KEY,
    coach_id integer REFERENCES Coach(coach_id),
    client_id integer REFERENCES Client(client_id),
    starting_measurement_id integer REFERENCES Measurement(measurement_id),
    goal_measurement_id integer REFERENCES Measurement(measurement_id),
    start_date date,
    end_date date,
    monthly_price numeric(10, 2)
);

CREATE TABLE Workout (
    workout_id serial PRIMARY KEY,
    contract_id integer REFERENCES Contract(contract_id),
    number_of_exercises integer,
    warming_up_time_in_seconds integer,
    number_of_sets integer,
    pause_between_sets_in_seconds integer,
    self_rating integer
);

CREATE TABLE Exercise (
    exercise_id serial PRIMARY KEY,
    name varchar(30),
    description text,
    equipment_needed text,
    difficulty_level difficulty_enum
);

CREATE TABLE WorkoutSession (
    workout_session_id serial PRIMARY KEY,
    workout_id integer REFERENCES Workout(workout_id),
    exercise_id integer REFERENCES Exercise(exercise_id),
    number_of_reps integer,
    pause_after_exercise_in_seconds integer,
    weight numeric(6, 2)
);
