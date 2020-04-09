package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingBoyTest {

    private ParkingBoy parkingBoy;

    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car(){
        ParkingBoy parkingBoy = new ParkingBoy();

        ParkingTicket parkingTicket = parkingBoy.park(new Car());

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch_car_with_parking_ticket(){
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.park(new Car());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_not_return_car_when_parking_boy_fetch_car_with_incorrect_parking_ticket(){
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.park(new Car());

        Car fetchedCar = parkingBoy.fetch(new ParkingTicket());
        Assert.assertNull(fetchedCar);
    }

//    @Test
//    public void should_not_return_car_when_ticket_has_used(){
//        ParkingBoy parkingBoy = new ParkingBoy();
//        ParkingTicket parkingTicket = parkingBoy.park(new Car());
//        parkingBoy.fetch(parkingTicket);
//        Assert.assertNull(fetchedCar);
//    }
}
