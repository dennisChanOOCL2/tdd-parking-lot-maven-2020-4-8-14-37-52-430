package com.oocl;

import java.util.*;
import java.util.stream.Stream;

public class ParkingBoyManager extends ParkingBoy {

    private List<ParkingBoy> parkingBoyList = new ArrayList<>();

    public ParkingBoyManager(ParkingLot... parkingLots){
        super(parkingLots);
    }

    public void assignParkingBoy(ParkingBoy... parkingBoys){
        parkingBoyList.addAll(Arrays.asList(parkingBoys));
    }

    public ParkingTicket assignParkingBoyParkCar(Car car){

        for (ParkingBoy parkingBoy : parkingBoyList) {
            try {
                ParkingTicket returnTicket = parkingBoy.park(car);
                return returnTicket;
            } catch (RuntimeException expectedExpcetion) {
            }
        }
        throw new NotEnoughPositionException();

    }

    public Car assignParkingBoyFetchCar(ParkingTicket parkingTicket){
        checkTickNotFound(parkingTicket);
        for (ParkingBoy parkingBoy : parkingBoyList) {
            try {
                Car car = parkingBoy.fetch(parkingTicket);
                return car;
            } catch (RuntimeException expectedExpcetion) {
            }
        }
        throw new UnrecognizedParkingTicketException();
    }

}
