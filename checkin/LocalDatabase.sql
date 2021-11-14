/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  mateus
 * Created: 14 de nov de 2021
 */

CREATE DATABASE event;

CREATE USER event@localhost IDENTIFIED BY 'event';

GRANT ALL PRIVILEGES ON event . * TO event@localhost;

FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS events (
    id INT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    event_date VARCHAR(255),
    sync TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    sync TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS event_registrations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    checkin TINYINT,
    activated TINYINT,
    email_sent TINYINT,
    certificate VARCHAR(255),
    user_email VARCHAR(255),
    event_id INT,
    sync TINYINT NOT NULL
);