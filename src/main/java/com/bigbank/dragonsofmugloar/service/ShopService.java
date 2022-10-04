package com.bigbank.dragonsofmugloar.service;

import com.bigbank.dragonsofmugloar.rest.RestClient;
import com.bigbank.dragonsofmugloar.rest.dto.ShopItem;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.bigbank.dragonsofmugloar.DragonsOfMugloarApplication.print;
import static com.bigbank.dragonsofmugloar.utils.GameMessage.*;
@Service
public class ShopService {

    private final RestClient restClient;

    public ShopService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Map<String, ShopItem> getItems(String gameId) {
        Map<String, ShopItem> map = new HashMap<>();
        var items = restClient.items(gameId);

        for (ShopItem item : items) {
            map.put(item.getName().toLowerCase(), item);
            print(item.toString());
        }

        return map;
    }

    public void buyItem(BufferedReader reader, String gameId, Map<String, ShopItem> map) throws IOException {
        String input = reader.readLine();
        var shopItem = map.get(input);

        if (shopItem != null) {
            var purchase = restClient.buyItem(gameId, shopItem.getId());

            if (Boolean.TRUE.toString().toLowerCase().equals(purchase.getShoppingSuccess())) {
                print(SUCCESS);
                print(purchase.toString());
            } else {
                print(NO_GOLD);
            }
        } else {
            print(INCORRECT_ITEM);
            buyItem(reader, gameId, map);
        }
    }
}
