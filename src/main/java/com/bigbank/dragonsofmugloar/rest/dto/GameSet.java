package com.bigbank.dragonsofmugloar.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static java.lang.String.format;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class GameSet {

    private String gameId;
    private Integer lives;
    private Integer gold;
    private Integer level;
    private Integer score;
    private Integer highScore;
    private Integer turn;

    @Override
    public String toString() {
        return format("lives: %d, gold: %d, level: %d, score: %d, highScore: %d",
                getLives(), getGold(), getLevel(), getScore(), getHighScore());
    }
}
