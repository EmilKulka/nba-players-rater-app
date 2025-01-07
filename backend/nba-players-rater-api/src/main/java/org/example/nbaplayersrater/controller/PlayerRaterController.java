package org.example.nbaplayersrater.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.nbaplayersrater.PlayerRaterFacade;
import org.example.nbaplayersrater.dto.AnswerDto;
import org.example.nbaplayersrater.dto.MatchupDto;
import org.example.nbaplayersrater.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlayerRaterController {
    private final PlayerRaterFacade playerRaterFacade;

    @GetMapping("/matchup")
    public ResponseEntity<MatchupDto> getMatchup() {
        MatchupDto matchupDto = playerRaterFacade.generateMatchup();
        return new ResponseEntity<>(matchupDto, HttpStatus.OK);
    }

    @PostMapping("/answer")
    public ResponseEntity<Void> answer(@RequestBody @Valid AnswerDto answerDto) {
        playerRaterFacade.processAnswer(answerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<Player>> getLeaderboard() {
        List<Player> leaderboard = playerRaterFacade.getTop100Players();
        return new ResponseEntity<>(leaderboard, HttpStatus.OK);
    }
}
