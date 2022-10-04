package com.bigbank.dragonsofmugloar.service;

import com.bigbank.dragonsofmugloar.rest.dto.GameSet;
import com.bigbank.dragonsofmugloar.rest.RestClient;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;

import static com.bigbank.dragonsofmugloar.DragonsOfMugloarApplication.print;
import static com.bigbank.dragonsofmugloar.utils.GameMessage.*;
@Service
public class GameService {

    private final RestClient restClient;

    public GameService(RestClient restClient) {
        this.restClient = restClient;
    }

    public GameSet initGame(BufferedReader reader) throws IOException {
        print(WELCOME);
        print(TYPE_START);
        GameSet gameSet;
        String input = reader.readLine();
        if (START.equals(input)) {
            gameSet = restClient.initGame();
            print("Game set - " + gameSet);
        } else {
            gameSet = initGame(reader);
        }

        return gameSet;
    }
}
