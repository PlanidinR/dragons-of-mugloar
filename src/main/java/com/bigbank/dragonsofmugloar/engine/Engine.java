package com.bigbank.dragonsofmugloar.engine;

import com.bigbank.dragonsofmugloar.rest.dto.GameSet;
import com.bigbank.dragonsofmugloar.service.AdsService;
import com.bigbank.dragonsofmugloar.service.GameService;
import com.bigbank.dragonsofmugloar.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

import static com.bigbank.dragonsofmugloar.DragonsOfMugloarApplication.print;
import static com.bigbank.dragonsofmugloar.utils.GameMessage.*;
import static com.bigbank.dragonsofmugloar.utils.ReaderUtils.getReader;
@Component
@RequiredArgsConstructor
public class Engine {

    private final GameService gameService;
    private final AdsService adsService;
    private final ShopService shopService;

    public void run() throws IOException {
        var reader = getReader();
        var gameSet = gameService.initGame(reader);
        mission(reader, gameSet);
    }

    private void mission(BufferedReader reader, GameSet gameSet) throws IOException {
        checkLivesAndScore(reader, gameSet.getLives(), gameSet.getScore());
        print(ADS);
        String input = reader.readLine();
        if (ANSWER_YES.equals(input)) {
            print(SELECT_MISSION);
            var ads = adsService.getAds(gameSet.getGameId());
            var missionResult = adsService.selectMission(reader, gameSet.getGameId(), ads);
            checkLivesAndScore(reader, missionResult.getLives(), missionResult.getScore());
        } else {
            shop(reader, gameSet.getGameId());
        }

        mission(reader, gameSet);
    }

    private void shop(BufferedReader reader, String gameId) throws IOException {
        String input;
        print(ITEMS);
        input = reader.readLine();
        if (ANSWER_YES.equals(input)) {
            var items = shopService.getItems(gameId);
            print(BUY_ITEM);
            shopService.buyItem(reader, gameId, items);
        }
    }

    private void checkLivesAndScore(BufferedReader reader, Integer lives, Integer score) throws IOException {
        int maxScore = 1000;
        int minLive = 0;
        if (lives <= minLive) {
            print(LOSE);
            if (restart(reader)) {
                run();
            } else {
                System.exit(0);
            }
        } else {
            if (score >= maxScore) {
                print(WIN);
                if (restart(reader)) {
                    run();
                } else {
                    System.exit(0);
                }
            }
        }
    }

    private boolean restart(BufferedReader reader) throws IOException {
        print(RESTART);
        String input = reader.readLine();
        if (ANSWER_YES.equals(input)) {
            return true;
        } else {
            print(END);
            return false;
        }
    }
}
