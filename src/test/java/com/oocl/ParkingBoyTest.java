package com.oocl;

import org.junit.Test;

public class ParkingBoyTest {

    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car(){
        ParkingBoy parkingBoy = new ParkingBoy();

        ParkingTicket parkingTicket = parkingBoy.park(new Car());


    }
}
