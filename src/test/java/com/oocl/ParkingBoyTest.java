package com.oocl;

import org.junit.Assert;
import org.junit.Test;

/**

As a customer, I would like to give my car to a parking boy so that he can help me park and fetch it.

AC1: The parking boy can park a car into the parking lot and returns a parking ticket.
 The customer can give the parking ticket back to the parking boy to fetch the car.

AC2: The parking boy can park multiple cars into the parking lot.
 And can fetch right car using correspond ticket.

AC3: If the customer gives a wrong ticket (the parking boy did not provide the ticket)
 or does not give a ticket. Then no car should be fetched.

AC4: If the customer gives a ticket that has already been used.
 Then no car should be fetched.

AC5: The parking lot has a capacity (the default capacity of a parking lot is 10).
 If there is no position, then the user cannot park the car into it. Thus (s)he will not get any ticket.

There are some cases which are not a requirement but may happen technically
Passing a parked car to a parking boy.
Passing a null car to a parking boy.



 */
public class ParkingBoyTest {

    @Test
    public void should_park_car_to_parking_lot(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCarFromParkingLot = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(fetchedCarFromParkingLot, car);
    }

    @Test
    public void should_return_null_for_parking_parked_car(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        parkingBoy.park(car);
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCarFromParkingLot = parkingLot.fetch(parkingTicket);

        Assert.assertNull(fetchedCarFromParkingLot);
    }



}
