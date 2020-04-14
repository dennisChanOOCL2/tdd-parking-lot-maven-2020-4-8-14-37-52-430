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
    public ParkingLot selectParkingLot(){
        return findParkingLotIsNotFull().stream()
                .max(Comparator.comparing(ParkingLot::getRemainPosition))
                .orElse(null);
    }

}
