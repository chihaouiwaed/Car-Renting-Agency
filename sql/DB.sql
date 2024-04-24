/* creation of database */
CREATE DATABASE allocationvoiture;
USE allocationvoiture;

/* create tables */

/* User table */
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/* car table */
CREATE  TABLE car(registrationNumber VARCHAR(50) PRIMARY KEY, 
					brand VARCHAR(50), 
					model VARCHAR(50), 
					price DOUBLE);
/* client table */
CREATE TABLE client(CIN VARCHAR(50) PRIMARY KEY, 
					firstName VARCHAR(50), 
					lastName VARCHAR(50));
/* ranting table */
CREATE TABLE ranting(rantID INT PRIMARY KEY AUTO_INCREMENT, 
					registrationNumber VARCHAR(50), 
					CIN VARCHAR(50), 
					isReturned BIT, 
					rentalDate DATE, 
					returnDate DATE);

/* CAR DATA */

INSERT INTO car VALUES ('PQI8524', 'Toyota', 'Camry', 28999);
INSERT INTO car VALUES ('JHF4567', 'Honda', 'Civic', 21999);
INSERT INTO car VALUES ('WRE1234', 'Ford', 'Focus', 18999);
INSERT INTO car VALUES ('GTR6789', 'Chevrolet', 'Malibu', 25999);
INSERT INTO car VALUES ('KLP4563', 'Hyundai', 'Elantra', 20999);
INSERT INTO car VALUES ('RTY7890', 'Nissan', 'Altima', 26999);
INSERT INTO car VALUES ('UYT3210', 'Volkswagen', 'Jetta', 23999);
INSERT INTO car VALUES ('VBN0987', 'Subaru', 'Impreza', 27999);
INSERT INTO car VALUES ('ZXC6543', 'Mazda', 'Mazda3', 24999);
INSERT INTO car VALUES ('QWE9876', 'Kia', 'Forte', 19999);


/* CLIENT DATA */

INSERT INTO client VALUES ('A013', 'Smith', 'John');
INSERT INTO client VALUES ('A014', 'Johnson', 'Emily');
INSERT INTO client VALUES ('A015', 'Williams', 'David');
INSERT INTO client VALUES ('A016', 'Brown', 'Jennifer');
INSERT INTO client VALUES ('A017', 'Jones', 'Michael');
INSERT INTO client VALUES ('A018', 'Miller', 'Jessica');
INSERT INTO client VALUES ('A019', 'Davis', 'Matthew');
INSERT INTO client VALUES ('A020', 'Garcia', 'Amanda');
INSERT INTO client VALUES ('A021', 'Rodriguez', 'Daniel');
INSERT INTO client VALUES ('A022', 'Martinez', 'Sarah');

/* USER DATA */
INSERT INTO users (username, password, role, created_at, updated_at)
VALUES
('john_doe', 'password123', 'admin', '2024-04-17 12:00:00', '2024-04-17 12:00:00'),
('jane_smith', 'letmein', 'user', '2024-04-17 12:15:00', '2024-04-17 12:15:00'),
('mike_jones', 'securepassword', 'user', '2024-04-17 12:30:00', '2024-04-17 12:30:00');
