package com.bigbank.dragonsofmugloar.service;

import com.bigbank.dragonsofmugloar.rest.RestClient;
import com.bigbank.dragonsofmugloar.rest.dto.Ad;
import com.bigbank.dragonsofmugloar.rest.dto.MissionResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.bigbank.dragonsofmugloar.utils.GameMessage.ANSWER_YES;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(AdsService.class)
class AdsServiceTest {

    @Autowired
    private AdsService adsService;
    @MockBean
    private RestClient restClient;
    @Mock
    private BufferedReader reader;

    private String gameId = "1";

    @Test
    void getAds() {
        var ad1 = new Ad("", "", "", 0);
        var ad2 = new Ad("", "", "", 2);

        when(restClient.ads(gameId)).thenReturn(new Ad[]{ad1,ad2});

        var result = adsService.getAds(gameId);
        assertEquals(ad1, result.get("1"));
        assertEquals(ad2, result.get("2"));
    }

    @Test
    void selectMission() throws IOException {
        var ad1 = new Ad("", "", "", 0);
        var ad2 = new Ad("", "", "", 2);
        Map<String, Ad> map = new HashMap<>();
        map.put("1", ad1);
        map.put("2", ad2);
        var missionResult = new MissionResult(true, 1,1,1,1,1,"");

        when(reader.readLine()).thenReturn("1");
        when(restClient.selectMission(gameId, ad1.getAdId())).thenReturn(missionResult);

        var result = adsService.selectMission(reader, gameId, map);

        assertEquals(missionResult, result);
    }

    @Test
    void selectMission_incorrect_type() throws IOException {
        var ad1 = new Ad("", "", "", 0);
        var ad2 = new Ad("", "", "", 2);
        Map<String, Ad> map = new HashMap<>();
        map.put("1", ad1);
        map.put("2", ad2);
        var missionResult = new MissionResult(true, 1,1,1,1,1,"");

        when(reader.readLine()).thenReturn("3");
        when(reader.readLine()).thenReturn("1");
        when(restClient.selectMission(gameId, ad1.getAdId())).thenReturn(missionResult);

        var result = adsService.selectMission(reader, gameId, map);

        assertEquals(missionResult, result);
    }
}