package com.oocl;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot... parkingLots){
        super(parkingLots);
    }

    @Override
    public ParkingLot selectAvailableParkingLot(){
        return findParkingLotIsNotFull().stream()
                .max(Comparator.comparing(ParkingLot::getPositionRate))
                .orElse(null);
    }
}
