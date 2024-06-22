package com.example.Parking.Model;

import com.example.Parking.Enum.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean paymentCompleted;

    private PaymentMode paymentMode;

    @OneToOne
    @JoinColumn
    Reservation reservation;
}

