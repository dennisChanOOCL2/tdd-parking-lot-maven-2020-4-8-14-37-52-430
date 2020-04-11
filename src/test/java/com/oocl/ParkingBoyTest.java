package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

 --- above case = demo showed ---

There are some cases which are not a requirement but may happen technically
Passing a parked car to a parking boy. (OK)
Passing a null car to a parking boy. (OK)



 */
public class ParkingBoyTest {

    private ParkingBoy parkingBoy;
    private ParkingLot parkingLot;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp(){
        this.parkingLot = new ParkingLot();
        this.parkingBoy = new ParkingBoy(parkingLot);
    }


    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car(){

        ParkingTicket parkingTicket = parkingBoy.park(new Car());

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch_car_with_parking_ticket(){

        parkingBoy.park(new Car());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_not_return_car_when_parking_boy_fetch_car_with_incorrect_parking_ticket(){

        parkingBoy.park(new Car());

        Car fetchedCar = parkingBoy.fetch(new ParkingTicket());
        Assert.assertNull(fetchedCar);
    }

    @Test
    public void should_not_return_car_when_ticket_has_used(){

        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        parkingBoy.fetch(parkingTicket);

        Car car = parkingBoy.fetch(parkingTicket);
        Assert.assertNull(car);
    }

    @Test
    public void should_not_park_car_when_parking_lot_is_full(){
        parkingLot = new ParkingLot(1);
        parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(new Car());

        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        Assert.assertNull(parkingTicket);
    }


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

    @Test
    public void should_return_null_for_parking_null(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket parkingTicket = parkingBoy.park(null);

        Car fetchedCarFromParkingLot = parkingLot.fetch(parkingTicket);

        Assert.assertNull(fetchedCarFromParkingLot);
    }

    @Test
    public void should_return_exception_message_when_fetch_with_incorrect_ticket() throws UnrecognizedParkingTicketException {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.fetch(new ParkingTicket());
    }

    @Test
    public void should_return_exception_message_when_no_ticket_provided() {
        expectedException.expect(TicketNotFoundException.class);
        expectedException.expectMessage("Please provide your parking ticket.");

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.fetch(null);
    }

    @Test
    public void should_return_exception_message_when_the_parking_lot_is_full() {
        expectedException.expect(NotEnoughPositionException.class);
        expectedException.expectMessage("Not enough position.");

        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(car);
        parkingBoy.park(car);

    }

    @Test
    public void should_park_car_to_second_parking_lot_when_first_parking_lot_is_full(){

        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        Assert.assertEquals(0, firstParkingLot.getRemainPosition());
        Assert.assertEquals(0, secondParkingLot.getRemainPosition());
    }


}
