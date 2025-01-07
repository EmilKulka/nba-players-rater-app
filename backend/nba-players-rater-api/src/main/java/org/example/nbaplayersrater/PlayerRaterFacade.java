package org.example.nbaplayersrater;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nbaplayersrater.dto.AnswerDto;
import org.example.nbaplayersrater.dto.MatchupDto;
import org.example.nbaplayersrater.exceptions.PlayerDidNotParticipateInMatchupException;
import org.example.nbaplayersrater.exceptions.MatchupAlreadyAnsweredException;
import org.example.nbaplayersrater.model.*;
import org.example.nbaplayersrater.service.AnswerService;
import org.example.nbaplayersrater.service.MatchupService;
import org.example.nbaplayersrater.service.PlayerService;
import org.example.nbaplayersrater.utils.PlayerIdPairGenerator;
import org.example.nbaplayersrater.utils.ScoreCalculator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class PlayerRaterFacade {
    private final MatchupService matchupService;
    private final PlayerService playerService;
    private final AnswerService answerService;
    private final PlayerIdPairGenerator playerIdPairGenerator;
    private final ScoreCalculator scoreCalculator;


    public MatchupDto generateMatchup() {
        log.info("Entering method generateMatchup");

        PlayerIdPair playersIdPair = playerIdPairGenerator.generate();

        MatchupDto matchupDto = matchupService.createMatchup(playersIdPair);

        log.info("Successfully generated matchupDto {}", matchupDto);
        return matchupDto;
    }


    @Transactional
    public void processAnswer(AnswerDto answerDto) {
        log.info("Entering method processAnswer with parameter answerDto: {}", answerDto);

        UUID matchupId = answerDto.getMatchupId();
        Long winnerId = answerDto.getWinnerId();

        Matchup matchupEntity = matchupService.getMatchupById(matchupId);

        if (matchupEntity.getAnswer() != null) {
            log.error("Matchup with given id: {}, has already been answered", matchupId);
            throw new MatchupAlreadyAnsweredException("Matchup with ID: " + matchupId + " has already been answered.");
        }

        Player winnerEntity = playerService.getPlayerById(winnerId);


        Long playerId = matchupEntity.getPlayerId();
        Long player2Id = matchupEntity.getPlayer2Id();


        if (!winnerEntity.getId().equals(playerId) && !winnerEntity.getId().equals(player2Id)) {
            log.error("Player with given ID: {}, was not participating in matchup with ID: {}", winnerId, matchupId);
            throw new PlayerDidNotParticipateInMatchupException("Player with ID: " + answerDto.getWinnerId() + " was not participating in matchup.");
        }

        CalculatedScorePair calculatedScorePair;
        Answer answer;


        if (winnerId.equals(playerId)) {
            calculatedScorePair = scoreCalculator.calculateNewScores(playerId, player2Id);
            playerService.updatePlayerScore(playerId, calculatedScorePair.winnerScore());
            playerService.updatePlayerScore(player2Id, calculatedScorePair.loserScore());
            answer = answerService.createAnswer(new Answer(
                    matchupEntity,
                    playerId,
                    player2Id
            ));
        } else {
            calculatedScorePair = scoreCalculator.calculateNewScores(player2Id, playerId);
            playerService.updatePlayerScore(playerId, calculatedScorePair.loserScore());
            playerService.updatePlayerScore(player2Id, calculatedScorePair.winnerScore());
            answer = answerService.createAnswer(new Answer(
                    matchupEntity,
                    player2Id,
                    playerId
            ));
        }
        matchupService.markMatchupAsAnswered(matchupId, answer);

        log.info("Successfully processed answerDto");
    }

    public List<Player> getTop100Players() {
        return playerService.getPlayersByScoreDescending();
    }
}
