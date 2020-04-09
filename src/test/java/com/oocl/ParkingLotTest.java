package com.oocl;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car(){
        ParkingLot parkingLot = new ParkingLot();

        ParkingTicket parkingTicket = parkingLot.park(new Car());

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch_car_with_parking_ticket(){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(new Car());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        Car fetchedCar = parkingLot.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_not_return_car_when_parking_boy_fetch_car_with_incorrect_parking_ticket(){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(new Car());

        Car fetchedCar = parkingLot.fetch(new ParkingTicket());
        Assert.assertNull(fetchedCar);
    }

    @Test
    public void should_not_return_car_when_ticket_has_used(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.park(new Car());
        parkingLot.fetch(parkingTicket);

        Car car = parkingLot.fetch(parkingTicket);
        Assert.assertNull(car);
    }

    @Test
    public void should_not_park_car_when_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        ParkingTicket parkingTicket = parkingLot.park(new Car());
        Assert.assertNull(parkingTicket);
    }

}
