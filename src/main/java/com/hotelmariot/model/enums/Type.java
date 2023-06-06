package com.hotelmariot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
    STANDARD("Стандарт"),
    ECONOMY("Эконом"),
    VIP("ВИП");
    private final String name;
}

