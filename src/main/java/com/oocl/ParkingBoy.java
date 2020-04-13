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

        List<ParkingLot> availableParkingLotList = findParkingLotIsNotFull();

        if(availableParkingLotList.size() == 0){
            throw new NotEnoughPositionException();
        }

        return availableParkingLotList.get(0).park(car);
    }

    public List<ParkingLot> findParkingLotIsNotFull(){
        List<ParkingLot> availableParkingLotList = this.parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull()).collect(Collectors.toList());

        return availableParkingLotList;
    }

    public Car fetch(ParkingTicket parkingTicket){
        Car returnCar = null;
        checkTickNotFound(parkingTicket);

        for(ParkingLot parkingLot : parkingLotList){
            returnCar = parkingLot.fetch(parkingTicket);
            if(returnCar != null){
                break;
            }
        }

        if(returnCar == null){
            throw new UnrecognizedParkingTicketException();
        }
        return returnCar;
    }

    public void checkTickNotFound(ParkingTicket parkingTicket){
        if(parkingTicket == null){
            throw new TicketNotFoundException();
        }
    }

}
