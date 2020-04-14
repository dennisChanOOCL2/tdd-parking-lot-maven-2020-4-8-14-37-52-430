package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();

    public ParkingBoy(ParkingLot... parkingLots){
        parkingLotList.addAll(Arrays.asList(parkingLots));
    }

    public ParkingTicket park(Car car) {

        ParkingLot parkingLot = selectAvailableParkingLot();
        if(parkingLot == null){
            throw new NotEnoughPositionException();
        }
        return parkingLot.park(car);
    }

    public ParkingLot selectAvailableParkingLot(){
        return findParkingLotIsNotFull().stream().findFirst().orElse(null);
    }

    public List<ParkingLot> findParkingLotIsNotFull(){
        List<ParkingLot> availableParkingLotList = this.parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .collect(Collectors.toList());
        return availableParkingLotList;
    }

    public Car fetch(ParkingTicket parkingTicket){

        checkTickNotFound(parkingTicket);
        // stream
        for(ParkingLot parkingLot : parkingLotList){
            Car returnCar = parkingLot.fetch(parkingTicket);
            if(returnCar != null){
                return returnCar;
            }
        }
        throw new UnrecognizedParkingTicketException();

    }

    public void checkTickNotFound(ParkingTicket parkingTicket){
        if(parkingTicket == null){
            throw new TicketNotFoundException();
        }
    }

}
