
CREATE TYPE gender_enum AS ENUM ('male', 'female');
CREATE TYPE difficulty_enum AS ENUM ('very easy', 'easy', 'moderate', 'hard', 'very hard');


CREATE TABLE Users (
    user_id serial PRIMARY KEY,
    first_name varchar(30) NOT NULL,
    last_name varchar(30) NOT NULL,
    email varchar(30) NOT NULL,
    address text NOT NULL,
    phone_number varchar(20),
    gender gender_enum,
    age integer NOT NULL
);


CREATE TABLE Coach (
    coach_id serial PRIMARY KEY,
    user_id integer REFERENCES Users(user_id),
    years_of_experience integer NOT NULL,
    education text NOT NULL,
    monthly_price numeric(10, 2) NOT NULL
);


CREATE TABLE Client (
    client_id serial PRIMARY KEY,
    user_id integer REFERENCES Users(user_id),
    medical_condition text,
    injuries text
);


CREATE TABLE Measurement (
    measurement_id serial PRIMARY KEY,
    body_weight numeric(6, 2) NOT NULL,
    body_fat numeric(5, 2) NOT NULL,
    waist_circumference numeric(5, 2) NOT NULL,
    chest_circumference numeric(5, 2) NOT NULL,
    arm_circumference numeric(5, 2) NOT NULL,
    leg_circumference numeric(5, 2) NOT NULL
);

CREATE TABLE Contract (
    contract_id serial PRIMARY KEY,
    coach_id integer REFERENCES Coach(coach_id),
    client_id integer REFERENCES Client(client_id),
    starting_measurement_id integer REFERENCES Measurement(measurement_id),
    goal_measurement_id integer REFERENCES Measurement(measurement_id),
    start_date date NOT NULL,
    end_date date NOT NULL,
    monthly_price numeric(10, 2) NOT NULL
);

CREATE TABLE Workout (
    workout_id serial PRIMARY KEY,
    contract_id integer REFERENCES Contract(contract_id),
    number_of_exercises integer NOT NULL,
    warming_up_time_in_seconds integer NOT NULL,
    number_of_sets integer NOT NULL,
    pause_between_sets_in_seconds integer NOT NULL,
    self_rating integer
);

CREATE TABLE Exercise (
    exercise_id serial PRIMARY KEY,
    name varchar(30) NOT NULL,
    description text NOT NULL,
    equipment_needed text NOT NULL,
    difficulty_level difficulty_enum
);

CREATE TABLE WorkoutSession (
    workout_session_id serial PRIMARY KEY,
    workout_id integer REFERENCES Workout(workout_id),
    exercise_id integer REFERENCES Exercise(exercise_id),
    number_of_reps integer NOT NULL,
    pause_after_exercise_in_seconds integer NOT NULL,
    weight numeric(6, 2) NOT NULL
);
