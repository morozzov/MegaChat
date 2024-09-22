-- liquibase formatted sql

-- changeset Roman Morozov:2024_09_22_14_20-1
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name     VARCHAR(255) DEFAULT NULL,
    role     VARCHAR(255) NOT NULL
);
-- rollback drop table users;