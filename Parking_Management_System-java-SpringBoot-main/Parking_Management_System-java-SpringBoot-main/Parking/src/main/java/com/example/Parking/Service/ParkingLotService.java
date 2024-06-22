package com.example.Parking.Service;

import com.example.Parking.DTO.SpotRequestDTO;
import com.example.Parking.DTO.SpotResponseDTO;
import com.example.Parking.Enum.SpotType;
import com.example.Parking.Model.ParkingLot;
import com.example.Parking.Model.Spot;
import com.example.Parking.Repository.ParkingLotRepository;
import com.example.Parking.Repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    SpotRepository spotRepository;

    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
        parkingLotRepository.save(parkingLot);

        return parkingLot;
    }

    public SpotResponseDTO addSpot(SpotRequestDTO spotRequestDTO) {
        ParkingLot parkingLot = parkingLotRepository.findById(spotRequestDTO.getId()).get();

        Spot spot = new Spot();
        spot.setPricePerHour(spotRequestDTO.getPph());
        spot.setOccupied(Boolean.FALSE);
        if(spotRequestDTO.getNw() == 2) spot.setSpotType(SpotType.TWO_WHEELER);
        else if(spotRequestDTO.getNw() == 4) spot.setSpotType(SpotType.FOUR_WHEELER);
        else spot.setSpotType(SpotType.OTHERS);
        spot.setParkingLot(parkingLot);;

        parkingLot.getSpotList().add(spot);

        ParkingLot updatedparking = parkingLotRepository.save(parkingLot);
        Spot saveSpot = updatedparking.getSpotList().get(updatedparking.getSpotList().size()-1);
        SpotResponseDTO spotResponseDTO = new SpotResponseDTO();
        spotResponseDTO.setId(updatedparking.getId());
        spotResponseDTO.setNw(spotRequestDTO.getNw());
        spotResponseDTO.setPph(saveSpot.getPricePerHour());
        spotResponseDTO.setSpotId(saveSpot.getId());

        return spotResponseDTO;
    }

    public void deleteSpot(int spotId) {
        Spot spot = spotRepository.findById(spotId).get();
        ParkingLot parkingLot = spot.getParkingLot();
        parkingLot.getSpotList().remove(spot);

        spotRepository.delete(spot);
        parkingLotRepository.save(parkingLot);
    }

    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).get();
        Spot spot = spotRepository.findById(spotId).get();
        parkingLot.getSpotList().remove(spot);
        spot.setPricePerHour(pricePerHour);
        parkingLot.getSpotList().add(spot);

        parkingLotRepository.save(parkingLot);

        return spot;
    }
}
