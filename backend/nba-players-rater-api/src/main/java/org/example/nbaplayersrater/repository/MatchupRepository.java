package org.example.nbaplayersrater.repository;

import org.example.nbaplayersrater.model.Matchup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchupRepository extends JpaRepository<Matchup, UUID> {
}
