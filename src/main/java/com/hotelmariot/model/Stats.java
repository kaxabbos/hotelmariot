package com.hotelmariot.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Stats {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Rooms room;
    private int days;

    public Stats(Rooms room) {
        this.room = room;
        this.days = 0;
    }
}
