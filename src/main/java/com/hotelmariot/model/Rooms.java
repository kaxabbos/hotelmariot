package com.hotelmariot.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rooms {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private int price;
    private String[] photos;
    private boolean free;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RoomsDescription description;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Stats stats;

    public Rooms(String name, int price, boolean free, String[] photos) {
        this.name = name;
        this.price = price;
        this.free = free;
        this.photos = photos;
    }

    public String[] photosAfter() {
        String[] res = new String[photos.length - 1];
        System.arraycopy(photos, 1, res, 0, photos.length - 1);
        return res;
    }
}
