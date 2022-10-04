package com.bigbank.dragonsofmugloar.engine;

import com.bigbank.dragonsofmugloar.rest.dto.Ad;
import com.bigbank.dragonsofmugloar.rest.dto.GameSet;
import com.bigbank.dragonsofmugloar.rest.dto.MissionResult;
import com.bigbank.dragonsofmugloar.service.AdsService;
import com.bigbank.dragonsofmugloar.service.GameService;
import com.bigbank.dragonsofmugloar.service.ShopService;
import com.bigbank.dragonsofmugloar.utils.ReaderUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static com.bigbank.dragonsofmugloar.utils.GameMessage.ANSWER_YES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@Import(Engine.class)
class EngineTest {

    @Autowired
    private Engine engine;
    @MockBean
    private GameService gameService;
    @MockBean
    private ShopService shopService;
    @MockBean
    private AdsService adsService;
    @Mock
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Test
    void run() throws IOException {
        MockedStatic<ReaderUtils> readerUtilsMockedStatic = Mockito.mockStatic(ReaderUtils.class);
        readerUtilsMockedStatic.when(ReaderUtils::getReader).thenReturn(reader);
        assertEquals(reader, ReaderUtils.getReader());

        var gameSet = new GameSet("1", 1, 1, 1, 1, 1, 1);
        Map<String, Ad> ads = new HashMap<>();
        ads.put("1", new Ad("1", "", "", 2));

        when(gameService.initGame(reader)).thenReturn(gameSet);
        when(reader.readLine()).thenReturn(ANSWER_YES).thenReturn("no");
        when(adsService.getAds(gameSet.getGameId())).thenReturn(ads);
        when(adsService.selectMission(reader, gameSet.getGameId(), ads)).thenReturn(new MissionResult(true, 3, 0, 1000, 10, 1, ""));

        engine.run();
    }
}