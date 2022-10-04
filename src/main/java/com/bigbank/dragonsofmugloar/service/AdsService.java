package com.bigbank.dragonsofmugloar.service;

import com.bigbank.dragonsofmugloar.rest.RestClient;
import com.bigbank.dragonsofmugloar.rest.dto.Ad;
import com.bigbank.dragonsofmugloar.rest.dto.MissionResult;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.bigbank.dragonsofmugloar.DragonsOfMugloarApplication.print;
import static com.bigbank.dragonsofmugloar.utils.GameMessage.INCORRECT_MISSION;
import static com.bigbank.dragonsofmugloar.utils.GameMessage.TYPE_MISSION;
@Service
public class AdsService {

    private final RestClient restClient;

    public AdsService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Map<String, Ad> getAds(String gameId) {
        Map<String, Ad> map = new HashMap<>();

        var ads = restClient.ads(gameId);
        int count = 1;
        for (Ad ad : ads) {
            map.put(String.valueOf(count), ad);
            print("Mission " + count + " - " + ad.toString());
            count++;
        }
        return map;
    }

    public MissionResult selectMission(BufferedReader reader, String gameId, Map<String, Ad> map) throws IOException {
        print(TYPE_MISSION);
        MissionResult result;
        var input = reader.readLine();
        var ad = map.get(input);

        if (ad != null) {
            result = restClient.selectMission(gameId, ad.getAdId());
            print(result.getMessage() + result.toString());
        } else {
            print(INCORRECT_MISSION);
            result = selectMission(reader, gameId, map);
        }

        return result;
    }
}
