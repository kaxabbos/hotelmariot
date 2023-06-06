package com.hotelmariot.model;

import com.hotelmariot.model.enums.Type;
import com.hotelmariot.model.enums.Beds;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoomsDescription {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Beds beds;
    private int number;
    private String description;
    private int floor;

    public RoomsDescription(Type type, Beds beds, int number, String description, int floor) {
        this.type = type;
        this.beds = beds;
        this.number = number;
        this.description = description;
        this.floor = floor;
    }
}
