package com.bigbank.dragonsofmugloar.service;

import com.bigbank.dragonsofmugloar.rest.RestClient;
import com.bigbank.dragonsofmugloar.rest.dto.GameSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.IOException;

import static com.bigbank.dragonsofmugloar.utils.GameMessage.START;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(GameService.class)
class GameServiceTest {

    @Autowired
    private GameService gameService;
    @MockBean
    private RestClient restClient;
    @Mock
    private BufferedReader reader;

    @Test
    void initGame() throws IOException {
        var gameSet = new GameSet("1", 3,0,0,0,0,0);
        when(reader.readLine()).thenReturn(START);
        when(restClient.initGame()).thenReturn(gameSet);

        assertEquals(gameSet, gameService.initGame(reader));
    }

    @Test
    void initGame_no() throws IOException {
        var gameSet = new GameSet("1", 3,0,0,0,0,0);
        when(reader.readLine()).thenReturn("no");
        when(reader.readLine()).thenReturn(START);
        when(restClient.initGame()).thenReturn(gameSet);

        assertEquals(gameSet, gameService.initGame(reader));
    }

}