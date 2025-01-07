--liquibase formatted sql
--changeset ekulka:3

DROP TABLE IF EXISTS ANSWERS CASCADE ;

CREATE TABLE ANSWERS(
    id UUID PRIMARY KEY,
    winner_id BIGINT,
    loser_id BIGINT,
    answered_at TIMESTAMP,
    matchup_id UUID
);

