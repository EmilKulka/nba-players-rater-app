package org.example.nbaplayersrater.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class AnswerDto {
    @NotNull(message = "Matchup ID is mandatory!")
    private UUID matchupId;
    @NotNull(message = "Winner ID is mandatory!")
    private Long winnerId;



}
