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
        ParkingLot selectedParkingLot = this.parkingLotList.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().get();
        return selectedParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException {
        try{
            return this.parkingLotList.get(0).fetch(parkingTicket);
        }catch(UnrecognizedParkingTicketException e){
            throw e;
        }
    }
}
