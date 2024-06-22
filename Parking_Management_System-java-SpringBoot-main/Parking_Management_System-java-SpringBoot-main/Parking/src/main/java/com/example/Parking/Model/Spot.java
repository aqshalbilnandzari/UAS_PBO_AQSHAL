package com.example.Parking.Model;

import com.example.Parking.Enum.SpotType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;
    private int pricePerHour;
    private Boolean occupied;

    @ManyToOne
    @JoinColumn
    ParkingLot parkingLot;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL)
    List<Reservation> reservationList = new ArrayList<>();
}
