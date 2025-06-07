-- ===========================================
-- Note :
-- ON DELETE RESTRICT : Prevent deletion of a parent if it's still assigned to any child.
-- ON UPDATE CASCADE  : If the fk is updated in parent, it will also update in child.
-- ===========================================


-- ===========================================
-- USER ROLES TABLE
-- ===========================================
CREATE TABLE IF NOT EXISTS user_roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY, -- pk
    role_name VARCHAR(100) NOT NULL UNIQUE,  -- fk
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ===========================================
-- USERS TABLE
-- ===========================================
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY, -- pk
    user_role_id INT NOT NULL, -- fk
    user_name VARCHAR(100) UNIQUE,
    user_email VARCHAR(100) UNIQUE,
    user_password VARCHAR(255) NOT NULL, 
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (user_role_id) REFERENCES user_roles(role_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- ===========================================
-- PASSENGER PROFILES TABLE
-- ===========================================
CREATE TABLE IF NOT EXISTS passenger_profiles (
    passenger_id INT AUTO_INCREMENT PRIMARY KEY, -- pk
    passenger_user_id INT UNIQUE NOT NULL, -- fk
    passenger_full_name VARCHAR(150) NOT NULL,
    passenger_passport_no VARCHAR(50) NOT NULL UNIQUE,
    passenger_nationality VARCHAR(100) NOT NULL,
    passenger_contact_no VARCHAR(20) NOT NULL,
    passenger_emergency_contact VARCHAR(100),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 

    FOREIGN KEY (passenger_user_id) REFERENCES users(user_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- ===========================================
-- AIRPORTS TABLE
-- ===========================================
CREATE TABLE IF NOT EXISTS airports (
    airport_id INT AUTO_INCREMENT PRIMARY KEY, -- pk
    airport_code CHAR(3) NOT NULL UNIQUE,
    airport_name VARCHAR(100) NOT NULL,
    airport_city VARCHAR(100) NOT NULL,
    airport_country VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ===========================================
-- ROUTES TABLE
-- ===========================================
CREATE TABLE IF NOT EXISTS routes (
    route_id INT AUTO_INCREMENT PRIMARY KEY, -- pk
    route_origin_airport_id INT NOT NULL, -- fk
    route_destination_airport_id INT NOT NULL, -- fk
    route_distance_km DECIMAL(10,2),
    route_estimated_duration_minutes INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (route_origin_airport_id) REFERENCES airports(airport_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    FOREIGN KEY (route_destination_airport_id) REFERENCES airports(airport_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT unique_origin_destination UNIQUE (route_origin_airport_id, route_destination_airport_id),

    -- Ensures that a route cannot start and end at the same airport
    CONSTRAINT chk_different_airports CHECK (route_origin_airport_id <> route_destination_airport_id) 
);

-- ===========================================
-- AIRCRAFT MODELS TABLE
-- ===========================================
CREATE TABLE IF NOT EXISTS aircraft_models (
    aircraft_model_id INT AUTO_INCREMENT PRIMARY KEY, -- pk
    aircraft_model_manufacturer VARCHAR(100) NOT NULL,
    aircraft_model_name VARCHAR(100) NOT NULL,
    aircraft_model_seating_capacity INT,
    aircraft_model_size_category VARCHAR(20),
    aircraft_model_range_km DECIMAL(10,2), -- maximum distance an aircraft can fly on a full load of fuel
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ===========================================
-- FLIGHT_SCHEDULE TABLE

-- Note : This Stores the recurring schedule of an aircraft within a week.
-- ===========================================
CREATE TABLE IF NOT EXISTS flight_schedules (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    schedule_route_id INT NOT NULL,
    schedule_aircraft_model_id INT NOT NULL,
    schedule_flight_code VARCHAR(10) NOT NULL,
    schedule_airline_name VARCHAR(100),
    schedule_departure_time TIME NOT NULL,
    schedule_arrival_time TIME NOT NULL,
    schedule_days_of_week SET('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun') NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (schedule_route_id) REFERENCES routes(route_id)
        ON DELETE RESTRICT ON UPDATE CASCADE,

    FOREIGN KEY (schedule_aircraft_model_id) REFERENCES aircraft_models(aircraft_model_id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

-- ===========================================
-- FLIGHTS TABLE

-- Note : This is generated from flight_schedules by matching the day of the week
-- ===========================================
CREATE TABLE IF NOT EXISTS flights (
    flight_id INT AUTO_INCREMENT PRIMARY KEY, -- pk
    flight_schedule_id INT, -- fk
    flight_route_id INT NOT NULL, -- fk
    flight_aircraft_model_id INT NOT NULL, -- fk
    flight_code VARCHAR(20) NOT NULL UNIQUE,
    flight_departure_time DATETIME NOT NULL,
    flight_arrival_time DATETIME NOT NULL,
    flight_airline_name VARCHAR(100),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 

    FOREIGN KEY (flight_schedule_id) REFERENCES flight_schedules(schedule_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    FOREIGN KEY (flight_route_id) REFERENCES routes(route_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    FOREIGN KEY (flight_aircraft_model_id) REFERENCES aircraft_models(aircraft_model_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT chk_arrival_after_departure CHECK (flight_arrival_time > flight_departure_time)
);

-- ===========================================
-- BOOKING CLASSES TABLE
-- ===========================================
CREATE TABLE IF NOT EXISTS booking_classes (
    booking_class_id INT AUTO_INCREMENT PRIMARY KEY, -- pk
    booking_class_name VARCHAR(50) NOT NULL UNIQUE, --  First Class, Business Class, and Economy Class.
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ===========================================
-- FLIGHT CLASS PRICES TABLE

-- Assumption : The same airline can offer multiple flights on the same route at different prices
-- Reasons:
--  Time of Day
--  Flight Duration (Nonstop flights / layovers)
--  Aircraft Type
-- ===========================================
CREATE TABLE IF NOT EXISTS flight_class_prices (
    flight_class_price_flight_id INT NOT NULL, -- fk
    flight_class_price_booking_class_id INT NOT NULL, -- fk
    flight_class_price_base_price DECIMAL(10,2) NOT NULL,
    flight_class_price_seat_capacity INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (flight_class_price_flight_id, flight_class_price_booking_class_id), -- composite PK

    FOREIGN KEY (flight_class_price_flight_id) REFERENCES flights(flight_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    FOREIGN KEY (flight_class_price_booking_class_id) REFERENCES booking_classes(booking_class_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT chk_base_price CHECK (flight_class_price_base_price >= 0),
    CONSTRAINT chk_seat_capacity CHECK (flight_class_price_seat_capacity >= 0)
);

-- ===========================================
-- RESERVATIONS TABLE
-- ===========================================
CREATE TABLE IF NOT EXISTS reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY, -- pk
    reservation_passenger_id INT NOT NULL, -- fk
    reservation_flight_id INT NOT NULL, -- fk
    reservation_booking_class_id INT NOT NULL, -- fk
    reservation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    reservation_seat_number VARCHAR(100),
    reservation_total_price DECIMAL(10,2) NOT NULL,
    reservation_status VARCHAR(50) NOT NULL, -- status : 'Confirmed', 'Cancelled'
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 

    FOREIGN KEY (reservation_passenger_id) REFERENCES passenger_profiles(passenger_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    FOREIGN KEY (reservation_flight_id) REFERENCES flights(flight_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    FOREIGN KEY (reservation_booking_class_id) REFERENCES booking_classes(booking_class_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    FOREIGN KEY (reservation_flight_id, reservation_booking_class_id)
        REFERENCES flight_class_prices(flight_class_price_flight_id, flight_class_price_booking_class_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT chk_reservation_status CHECK (reservation_status IN ('Confirmed', 'Cancelled')), 
    CONSTRAINT uq_seat_per_flight UNIQUE (reservation_flight_id, reservation_seat_number) 
);
