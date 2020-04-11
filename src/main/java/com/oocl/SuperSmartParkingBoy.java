package com.oocl;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot... parkingLots){
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {

        List<ParkingLot> availableParkingLotList = findParkingLotIsNotFull();
        ParkingLot selectedParkingLot = availableParkingLotList.stream()
                .max(Comparator.comparing(ParkingLot::getPositionRate))
                .orElse(null);

        if(selectedParkingLot == null){
            throw new NotEnoughPositionException();
        }

        return selectedParkingLot.park(car);
    }

}
