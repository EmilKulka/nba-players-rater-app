package org.example.nbaplayersrater.utils;

import lombok.extern.slf4j.Slf4j;
import org.example.nbaplayersrater.model.PlayerIdPair;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
public class PlayerIdPairGenerator {
    private final SecureRandom random;
    private final static Long PLAYERIDBOUND = 615L;


    PlayerIdPairGenerator(SecureRandom random) {
        this.random = random;
    }

    public PlayerIdPair generate() {
        Long playerId = generateRandomPlayerId();
        Long player2Id = generateExceptPlayerId(playerId);

        PlayerIdPair playerIdPair = new PlayerIdPair(playerId, player2Id);

        return playerIdPair;
    }

    private Long generateExceptPlayerId(Long playerId) {
        Long exceptPlayerId = generateRandomPlayerId();

        if (exceptPlayerId.equals(playerId)) {
            return generateExceptPlayerId(playerId);
        }

        return exceptPlayerId;
    }

    private Long generateRandomPlayerId() {
        return random.nextLong(1, PLAYERIDBOUND);
    }


}
