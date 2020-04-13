package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ParkingBoyManager extends ParkingBoy {

    private List<ParkingBoy> parkingBoyList = new ArrayList<>();

    public ParkingBoyManager(ParkingLot... parkingLots){
        super(parkingLots);
    }

    public void assignParkingBoy(ParkingBoy... parkingBoys){
        parkingBoyList.addAll(Arrays.asList(parkingBoys));
    }

    public ParkingTicket assignParkingBoyParkCar(){
        return null;
    }

    public Car assignParkingBoyFetchCar(){
        return null;
    }

}
