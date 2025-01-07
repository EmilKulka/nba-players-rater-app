package org.example.nbaplayersrater.repository;

import org.example.nbaplayersrater.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
}
