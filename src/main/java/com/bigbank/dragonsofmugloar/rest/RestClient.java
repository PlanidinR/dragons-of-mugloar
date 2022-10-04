package com.bigbank.dragonsofmugloar.rest;

import com.bigbank.dragonsofmugloar.rest.dto.*;
import com.bigbank.dragonsofmugloar.exception.HttpRuntimeException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Retryable(value = HttpRuntimeException.class)
@Component
public class RestClient {

    private final RestTemplate restTemplate;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GameSet initGame() {
        var url = "https://dragonsofmugloar.com/api/v2/game/start";
        GameSet gameSet;

        try {
            gameSet = restTemplate.postForEntity(url, null, GameSet.class).getBody();
        } catch (Exception ex) {
            throw new HttpRuntimeException(ex);
        }

        return gameSet;
    }

    public Ad[] ads(String gameId) {
        var url = "https://dragonsofmugloar.com/api/v2/%s/messages";
        Ad[] ads;

        try {
            ads = restTemplate.getForObject(format(url, gameId), Ad[].class);
        } catch (Exception ex) {
            throw new HttpRuntimeException(ex);
        }

        return ads;
    }

    public MissionResult selectMission(String gameId, String adId) {
        var url = "https://dragonsofmugloar.com/api/v2/%s/solve/%s";
        MissionResult missionResult;

        try {
            missionResult = restTemplate.postForEntity(
                    format(url, gameId, adId), null, MissionResult.class).getBody();
        } catch (Exception ex) {
            throw new HttpRuntimeException(ex);
        }

        return missionResult;
    }

    public ShopItem[] items(String gameId) {
        var url = "https://dragonsofmugloar.com/api/v2/%s/shop";
        ShopItem[] items;

        try {
            items = restTemplate.getForObject(format(url, gameId), ShopItem[].class);
        } catch (Exception ex) {
            throw new HttpRuntimeException(ex);
        }

        return items;
    }

    public Purchase buyItem(String gameId, String itemId) {
        var url = "https://dragonsofmugloar.com/api/v2/%s/shop/buy/%s";
        Purchase purchase = null;
        try {
            purchase = restTemplate.postForEntity(format(url, gameId, itemId), null, Purchase.class).getBody();
        } catch (Exception ex) {
            throw new HttpRuntimeException(ex);
        }

        return purchase;
    }

}
