package com.oocl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class ParkingBoy {
    private int capacity;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<ParkingTicket, Car>();

    public ParkingBoy(int capacity){
        this.capacity = capacity;
    }

    public ParkingBoy(){
        this.capacity = 10;
    }

    public ParkingTicket park(Car car) {
        if (this.capacity == parkingTicketCarMap.size()){
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
