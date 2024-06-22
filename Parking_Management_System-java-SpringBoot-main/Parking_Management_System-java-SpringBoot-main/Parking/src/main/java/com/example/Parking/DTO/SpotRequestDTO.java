package com.example.Parking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpotRequestDTO {
    private int id;
    private int nw;
    private int pph;
}
