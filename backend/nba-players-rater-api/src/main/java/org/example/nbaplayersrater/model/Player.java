package org.example.nbaplayersrater.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Setter
    private int score;
    private String imgUrl;

    public Player(String name, String surname, Integer score, String imgUrl) {
        this.name = name;
        this.surname = surname;
        this.score = score;
        this.imgUrl = imgUrl;
    }

}
