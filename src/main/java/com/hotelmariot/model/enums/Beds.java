package com.hotelmariot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Beds {
    ONE("Один"),
    TWO("Два"),
    FOUR("Четыре"),
    SIX("Шесть"),
    EIGHT("Восьм");
    private final String name;
}

