package org.example.nbaplayersrater.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerIdPair {
    private Long playerId;
    private Long player2Id;
}
