package com.hotelmariot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    ADMIN("Владелец"),
    MANAGER("Управляющий"),
    CLIENT("Постоялец");

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }
}

