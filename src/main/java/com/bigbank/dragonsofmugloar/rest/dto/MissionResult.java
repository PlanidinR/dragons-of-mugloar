package com.bigbank.dragonsofmugloar.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class MissionResult {

    private Boolean success;
    private Integer lives;
    private Integer gold;
    private Integer score;
    private Integer highScore;
    private Integer turn;
    private String message;

    @Override
    public String toString() {
        return format("lives: %d, gold: %d, score: %d, highScore: %d",
                getLives(), getGold(), getScore(), getHighScore());
    }
}
