package com.bigbank.dragonsofmugloar.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static java.lang.String.format;

@Getter
@Setter
@AllArgsConstructor
public class Ad {

    private String adId;
    private String message;
    private String reward;
    private Integer expiresIn;

    @Override
    public String toString() {
        return format("message: %s, reward: %s, expiresIn: %d",
                getMessage(), getReward(), getExpiresIn());
    }
}
