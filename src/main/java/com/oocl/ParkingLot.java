package com.oocl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<ParkingTicket, Car>();
    private List<ParkingBoy> parkingBoyList = new ArrayList();

    public void assignParkingBoyToLot(ParkingBoy parkingBoy){
        if(!parkingBoyList.contains(parkingBoy)){
            parkingBoyList.add(parkingBoy);
        }
    }

    public ParkingLot(int capacity){
        this.capacity = capacity;
    }

    public ParkingLot(){
        this.capacity = 10;
    }

    public ParkingTicket park(Car car) {
        if (car == null
                || this.capacity == parkingTicketCarMap.size()
                || parkingTicketCarMap.containsValue(car)){
            return null;
        }

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket){
        Car car= parkingTicketCarMap.remove(parkingTicket);
        return car;
    }

}
