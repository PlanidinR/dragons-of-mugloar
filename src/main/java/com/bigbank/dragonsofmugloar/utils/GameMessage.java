package com.bigbank.dragonsofmugloar.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameMessage {
    public static final String FAILED_CONNECT = "Oops! Something wrong with server connect. Restart game.";
    public static final String ADS = "Do you want to see ads? Yes/No";
    public static final String ITEMS = "Do you want to buy any items? Yes/No";
    public static final String SELECT_MISSION = "Select a mission, Hero!";
    public static final String ANSWER_YES = "yes";
    public static final String BUY_ITEM = "Select an item";
    public static final String LOSE = "You lose...";
    public static final String WIN = "You win, Hero!";

    public static final String RESTART = "Do you want to start again? Yes/No";
    public static final String END = "The end.";
    public static final String TYPE_MISSION = "Type mission number";
    public static final String INCORRECT_MISSION = "Incorrect mission number! Select again";
    public static final String INCORRECT_ITEM = "Incorrect item! Select again";
    public static final String TYPE_START = "Type start";
    public static final String START = "start";
    public static final String NO_GOLD = "Not enough gold";
    public static final String SUCCESS = "Success!";
    public static final String WELCOME = "Welcome to Dragons of Mugloar!";
}
