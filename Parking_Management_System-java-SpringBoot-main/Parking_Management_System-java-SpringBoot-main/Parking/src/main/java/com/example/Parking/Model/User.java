package com.example.Parking.Model;

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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String phoneNumber;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Reservation> reservationList = new ArrayList<>();
}
