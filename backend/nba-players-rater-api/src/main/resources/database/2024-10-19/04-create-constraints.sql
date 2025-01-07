--liquibase formatted sql
--changeset ekulka:4

ALTER TABLE MATCHUPS
ADD CONSTRAINT fk_matchups_answers foreign key(answer_id) references answers(id);

ALter TABLE ANSWERS
ADD CONSTRAINT fk_answers_matchups foreign key(matchup_id) references matchups(id);