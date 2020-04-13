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

        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(5);

        ParkingBoy parkingBoyForFirstParkingLot = new ParkingBoy(firstParkingLot);
        ParkingBoy parkingBoyForSecondParkingLot = new ParkingBoy(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(firstParkingLot, secondParkingLot);

        parkingBoyForFirstParkingLot.park(new Car());
        parkingBoyForSecondParkingLot.park(new Car());
        parkingBoyForSecondParkingLot.park(new Car());
        parkingBoyForSecondParkingLot.park(new Car());

        superSmartParkingBoy.park(new Car());

        Assert.assertEquals(0, firstParkingLot.getRemainPosition());
        Assert.assertEquals(2, secondParkingLot.getRemainPosition());
    }


}
