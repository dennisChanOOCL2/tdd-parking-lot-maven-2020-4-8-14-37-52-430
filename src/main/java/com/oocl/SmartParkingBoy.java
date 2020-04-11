package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot... parkingLots){
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {

        List<ParkingLot> availableParkingLotList = findParkingLotIsNotFull();
        ParkingLot selectedParkingLot = availableParkingLotList.stream()
                .max(Comparator.comparing(ParkingLot::getRemainPosition))
                .orElse(null);

        if(selectedParkingLot == null){
            throw new NotEnoughPositionException();
        }

        return selectedParkingLot.park(car);
    }

}
