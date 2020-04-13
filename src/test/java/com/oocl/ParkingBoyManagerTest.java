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
    public void should_return_null_when_manager_assign_parking_boy_to_fetch_car_from_a_parking_lot_which_is_not_manageed_by_him(){
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

}
