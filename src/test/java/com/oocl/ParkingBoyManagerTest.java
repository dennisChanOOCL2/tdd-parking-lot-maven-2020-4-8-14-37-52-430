package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ParkingBoyManagerTest {

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
    public void assign_parking_boy_to_park_car(){

        ParkingLot firstParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoyForFirstParkingLot = new ParkingBoy(firstParkingLot);
        ParkingBoyManager parkingBoyManager = new ParkingBoyManager();

        parkingBoyManager.assignParkingBoy(parkingBoyForFirstParkingLot);
        ParkingTicket parkingTicket = parkingBoyManager.assignParkingBoyParkCar(new Car());

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void assign_parking_boy_to_fetch_car(){

        ParkingLot firstParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoyForFirstParkingLot = new ParkingBoy(firstParkingLot);
        ParkingBoyManager parkingBoyManager = new ParkingBoyManager();
        Car car = new Car();
        parkingBoyManager.assignParkingBoy(parkingBoyForFirstParkingLot);
        ParkingTicket parkingTicket = parkingBoyManager.assignParkingBoyParkCar(car);

        Assert.assertEquals(car, parkingBoyManager.assignParkingBoyFetchCar(parkingTicket));
    }

    @Test
    public void should_return_null_when_manager_assign_parking_boy_to_fetch_car_from_a_parking_lot_which_is_not_managed_by_him(){
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");

        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoyForFirstParkingLot = new ParkingBoy(firstParkingLot);
        ParkingBoy parkingBoyForSecondParkingLot = new ParkingBoy(secondParkingLot);
        ParkingBoyManager parkingBoyManager = new ParkingBoyManager();
        Car car = new Car();
        parkingBoyManager.assignParkingBoy(parkingBoyForFirstParkingLot);

        ParkingTicket parkingTicket = parkingBoyForSecondParkingLot.park(car);
        Car fetchedCar = parkingBoyManager.assignParkingBoyFetchCar(parkingTicket);
        Assert.assertNull(fetchedCar);

    }

    @Test
    public void should_return_null_when_manager_try_to_fetch_car_from_a_parking_lot_which_is_not_managed_by_him(){
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");

        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoyForSecondParkingLot = new ParkingBoy(secondParkingLot);
        ParkingBoyManager parkingBoyManager = new ParkingBoyManager(firstParkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoyForSecondParkingLot.park(car);
        Car fetchedCar = parkingBoyManager.fetch(parkingTicket);
        Assert.assertNull(fetchedCar);
    }

    @Test
    public void should_not_show_error_message_if_manager_have_two_parking_boy_belong_to_different_parking_lot_and_he_told_them_to_fetch_a_car_which_is_park_from_one_of_the_parking_boy(){

        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoyForFirstParkingLot = new ParkingBoy(firstParkingLot);
        ParkingBoy parkingBoyForSecondParkingLot = new ParkingBoy(secondParkingLot);
        ParkingBoyManager parkingBoyManager = new ParkingBoyManager();
        Car car = new Car();
        parkingBoyManager.assignParkingBoy(parkingBoyForFirstParkingLot, parkingBoyForSecondParkingLot);
        ParkingTicket parkingTicket = parkingBoyManager.assignParkingBoyParkCar(car);

        Assert.assertEquals(car, parkingBoyManager.assignParkingBoyFetchCar(parkingTicket));
    }

    @Test
    public void should_return_exception_message_when_manager_assign_parking_boy_to_fetch_car_with_incorrect_ticket() throws UnrecognizedParkingTicketException {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");

        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        ParkingBoy parkingBoyForFirstParkingLot = new ParkingBoy(firstParkingLot);
        ParkingBoy parkingBoyForSecondParkingLot = new ParkingBoy(secondParkingLot);
        ParkingBoyManager parkingBoyManager = new ParkingBoyManager();

        parkingBoyManager.assignParkingBoy(parkingBoyForFirstParkingLot, parkingBoyForSecondParkingLot);

        parkingBoyManager.assignParkingBoyFetchCar(new ParkingTicket());

    }

    @Test
    public void should_return_exception_message_when_manager_assign_parking_boy_to_fetch_car_but_no_ticket_provided() {
        expectedException.expect(TicketNotFoundException.class);
        expectedException.expectMessage("Please provide your parking ticket.");

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.fetch(null);
    }

    @Test
    public void should_return_exception_message_when_manager_assign_parking_boy_to_fetch_car_but_not_enough_position() {
        expectedException.expect(NotEnoughPositionException.class);
        expectedException.expectMessage("Not enough position.");

        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

    }

}
