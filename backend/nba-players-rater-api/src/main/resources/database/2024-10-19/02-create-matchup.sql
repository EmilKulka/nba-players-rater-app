--liquibase formatted sql
--changeset ekulka:2

DROP TABLE IF EXISTS MATCHUPS CASCADE ;

CREATE TABLE MATCHUPS(
    id UUID PRIMARY KEY,
    player_id BIGINT,
    player2_id BIGINT,
    created_at TIMESTAMP,
    answer_id UUID
);