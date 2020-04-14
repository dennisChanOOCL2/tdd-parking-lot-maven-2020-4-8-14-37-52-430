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

        ParkingBoy selectedParkingBoy = parkingBoyList.stream()
                .filter(parkingBoy -> parkingBoy.findParkingLotIsNotFull() != null)
                .findFirst()
                .orElseThrow(() -> new NotEnoughPositionException());

        return selectedParkingBoy.park(car);

    }

    public Car assignParkingBoyFetchCar(ParkingTicket parkingTicket){
        checkTickNotFound(parkingTicket);

        ParkingBoy selectedParkingBoy = parkingBoyList.stream()
                .filter(parkingBoy -> parkingBoy.fetch(parkingTicket) != null)
                .findFirst()
                .orElseThrow(() -> new UnrecognizedParkingTicketException());

        return selectedParkingBoy.fetch(parkingTicket);
    }

}
