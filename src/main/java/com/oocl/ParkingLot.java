package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<ParkingTicket, Car>();
    private static final int DEFAULT_CAPACITY = 10;

    public ParkingLot(int capacity){
        this.capacity = capacity;
    }

    public ParkingLot(){
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingTicket park(Car car) {

        //different logic here (business / logic error)
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

    public boolean isFull() {
        if(this.capacity <= parkingTicketCarMap.size()){
            return true;
        }
        return false;
    }

    public int getRemainPosition() {
        return capacity - parkingTicketCarMap.size();
    }

    public double getPositionRate(){
        return (double) getRemainPosition()/capacity;
    }
}
