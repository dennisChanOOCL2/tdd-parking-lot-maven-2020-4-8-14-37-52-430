package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class SmartParkingBoyTest {

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
    public void smart_parking_boyshould_park_car_to_second_parking_lot_when_first_parking_lot_has_1_space_left_and_second_parking_lot_has_3_spaces_left(){

        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(3);

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        parkingBoy.park(new Car());
        smartParkingBoy.park(new Car());

        Assert.assertEquals(1, firstParkingLot.getRemainPosition());
        Assert.assertEquals(2, secondParkingLot.getRemainPosition());
    }


}
