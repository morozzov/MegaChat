-- liquibase formatted sql

-- changeset Roman Morozov:2024_09_22_15_00-1
ALTER TABLE IF EXISTS users
    ADD COLUMN IF NOT EXISTS is_active BOOLEAN NOT NULL DEFAULT true;