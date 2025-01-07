package org.example.nbaplayersrater.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nbaplayersrater.exceptions.PlayerDoesNotExistException;
import org.example.nbaplayersrater.model.Player;
import org.example.nbaplayersrater.repository.PlayerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerDoesNotExistException("Player with ID: " + id + " does not exist."));
        return player;
    }

    @Transactional
    public void updatePlayerScore(Long playerId, int newScore) {
        Player player = getPlayerById(playerId);
        player.setScore(newScore);
    }

    public List<Player> getPlayersByScoreDescending() {
        Pageable limit = PageRequest.of(0, 100);
        return playerRepository.findAllByOrderByScoreDesc(limit);
    }
}
