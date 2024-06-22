package com.example.Parking.Controller;

import com.example.Parking.DTO.SpotRequestDTO;
import com.example.Parking.DTO.SpotResponseDTO;
import com.example.Parking.Model.ParkingLot;
import com.example.Parking.Model.Spot;
import com.example.Parking.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingLot")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping("/add")
    public ResponseEntity<ParkingLot> addParkingLot(@RequestParam String name, @RequestParam String address) {
        //add a new parking lot to the database
        ParkingLot newParkingLot = parkingLotService.addParkingLot(name, address);
        return new ResponseEntity<>(newParkingLot, HttpStatus.CREATED);
    }

    @PostMapping("/spot/add")
    public ResponseEntity<SpotResponseDTO> addSpot(@RequestBody SpotRequestDTO spotRequestDTO) {
        //create a new spot in the parkingLot with given id
        //the spot type should be the next biggest type in case the number of wheels are not 2 or 4, for 4+ wheels, it is others
        SpotResponseDTO newSpot = parkingLotService.addSpot(spotRequestDTO);
        return new ResponseEntity<>(newSpot, HttpStatus.CREATED);
    }

    @DeleteMapping("/spot/delete")
    public ResponseEntity<Void> deleteSpot(@RequestParam("id") int spotId) {
        //delete a spot from given parking lot
        parkingLotService.deleteSpot(spotId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/spot/update")
    public ResponseEntity<Spot> updateSpot(@RequestParam("pid") int parkingLotId, @RequestParam("sid") int spotId, @RequestParam("pph") int pricePerHour) {
        //update the details of a spot
        Spot updatedSpot = parkingLotService.updateSpot(parkingLotId, spotId, pricePerHour);
        return new ResponseEntity<>(updatedSpot, HttpStatus.OK);
    }
}
