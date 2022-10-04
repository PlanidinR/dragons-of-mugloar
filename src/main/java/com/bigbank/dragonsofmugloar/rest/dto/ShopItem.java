package com.bigbank.dragonsofmugloar.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class ShopItem {

    private String id;
    private String name;
    private Integer cost;

    @Override
    public String toString() {
        return format("name: %s, cost: %d", getName(), getCost());
    }
}
