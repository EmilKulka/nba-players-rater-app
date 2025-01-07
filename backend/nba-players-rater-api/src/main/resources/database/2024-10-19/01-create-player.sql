--liquibase formatted sql
--changeset ekulka:1
DROP TABLE IF EXISTS PLAYERS;

CREATE TABLE PLAYERS (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(32),
    surname VARCHAR(32),
    score INT,
    img_url VARCHAR(255)
);