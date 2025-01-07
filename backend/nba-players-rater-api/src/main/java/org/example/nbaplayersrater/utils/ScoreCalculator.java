package org.example.nbaplayersrater.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nbaplayersrater.service.PlayerService;
import org.example.nbaplayersrater.model.CalculatedScorePair;
import org.example.nbaplayersrater.model.Player;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ScoreCalculator {
    private final PlayerService playerService;
    private final static int K = 30;

    private double calculateExpectedScore(int rating1, int rating2) {
        return 1.0 / (1 + Math.pow(10, (rating1 - rating2) / 400.0));
    }

    public CalculatedScorePair calculateNewScores(Long winnerId, Long loserId) {
        Player winner = playerService.getPlayerById(winnerId);
        Player loser = playerService.getPlayerById(loserId);

        int winnerCurrentScore = winner.getScore();
        int loserCurrentScore = loser.getScore();

        double expectedWinnerScore = calculateExpectedScore(winner.getScore(), loser.getScore());
        double expectedLoserScore = calculateExpectedScore(loser.getScore(), winner.getScore());

        int newWinnerScore = (int) (winnerCurrentScore + K * (1 - expectedWinnerScore));
        int newLoserScore = (int) (loserCurrentScore + K * ( (-1) * expectedLoserScore));

        return new CalculatedScorePair(newWinnerScore, newLoserScore);
    }

}
