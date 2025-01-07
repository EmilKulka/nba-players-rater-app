package org.example.nbaplayersrater.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "matchups")
public class Matchup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Long playerId;
    @Column(name = "player2_id")
    private Long player2Id;
    private LocalDateTime createdAt;
    @Setter
    @OneToOne
    private Answer answer;


    public Matchup(Long playerId, Long player2Id) {
        this.playerId = playerId;
        this.player2Id = player2Id;
        this.createdAt = LocalDateTime.now();
    }
}
