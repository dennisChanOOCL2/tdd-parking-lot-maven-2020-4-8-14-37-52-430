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
}
