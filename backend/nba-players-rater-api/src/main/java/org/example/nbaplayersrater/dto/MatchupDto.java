package org.example.nbaplayersrater.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MatchupDto {
    private UUID id;
    private Long playerId;
    private String playerName;
    private String playerSurname;
    private String playerImgUrl;
    private Long player2Id;
    private String player2Name;
    private String player2Surname;
    private String player2ImgUrl;
}
