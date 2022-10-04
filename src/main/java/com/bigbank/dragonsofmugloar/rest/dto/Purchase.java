package com.bigbank.dragonsofmugloar.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class Purchase {

    private String shoppingSuccess;
    private Integer gold;
    private Integer lives;
    private Integer level;
    private Integer turn;

    @Override
    public String toString() {
        return format("lives: %d, gold: %d, level: %d, turn: %d",
                getLives(), getGold(), getLevel(), getTurn());
    }
}
