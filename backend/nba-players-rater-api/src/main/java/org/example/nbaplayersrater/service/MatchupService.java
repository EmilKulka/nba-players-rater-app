package org.example.nbaplayersrater.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nbaplayersrater.dto.MatchupDto;
import org.example.nbaplayersrater.exceptions.MatchupAlreadyAnsweredException;
import org.example.nbaplayersrater.exceptions.MatchupDoesNotExistException;
import org.example.nbaplayersrater.mapper.MatchupMapper;
import org.example.nbaplayersrater.model.Answer;
import org.example.nbaplayersrater.model.Matchup;
import org.example.nbaplayersrater.model.Player;
import org.example.nbaplayersrater.model.PlayerIdPair;
import org.example.nbaplayersrater.repository.MatchupRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchupService {
    private final MatchupRepository matchupRepository;
    private final PlayerService playerService;
    private final MatchupMapper matchupMapper = MatchupMapper.INSTANCE;


    public MatchupDto createMatchup(PlayerIdPair playerIdPair) {
        Player player = playerService.getPlayerById(playerIdPair.getPlayerId());
        Player player2 = playerService.getPlayerById(playerIdPair.getPlayer2Id());

        Matchup matchup = new Matchup(
                playerIdPair.getPlayerId(),
                playerIdPair.getPlayer2Id());
        matchupRepository.save(matchup);

        MatchupDto matchupDto = matchupMapper.toMatchupDto(matchup, player, player2);

        return matchupDto;
    }

    public Matchup getMatchupById(UUID id) {
        Matchup matchup = matchupRepository.findById(id)
                .orElseThrow(() -> new MatchupDoesNotExistException("Matchup with ID: " + id + " doest not exist."));
        return matchup;
    }

    @Transactional
    public void markMatchupAsAnswered(UUID matchupId, Answer answer) {
        Matchup matchupEntity = getMatchupById(matchupId);

        if (matchupEntity.getAnswer() != null) {
            throw new MatchupAlreadyAnsweredException("Matchup with ID: " + matchupId + " has already been answered.");
        }

        matchupEntity.setAnswer(answer);
    }
}
