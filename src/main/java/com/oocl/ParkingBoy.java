package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();

    public ParkingBoy(ParkingLot... parkingLots){
        this.parkingLotList.addAll(Arrays.asList(parkingLots));
    }

    public ParkingTicket park(Car car) {
        return this.parkingLotList.get(0).park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException {
        return this.parkingLotList.get(0).fetch(parkingTicket);
    }
}
