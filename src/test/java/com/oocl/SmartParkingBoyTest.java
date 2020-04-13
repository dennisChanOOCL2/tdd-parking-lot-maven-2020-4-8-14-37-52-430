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
    public void smart_parking_boy_should_park_car_to_first_parking_lot_when_first_parking_lot_has_2_space_left_and_second_parking_lot_capacity_has_1_spaces_left(){

        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        smartParkingBoy.park(new Car());

        Assert.assertEquals(1, firstParkingLot.getRemainPosition());
        Assert.assertEquals(1, secondParkingLot.getRemainPosition());

    }

    @Test
    public void smart_parking_boy_should_park_car_to_second_parking_lot_when_first_parking_lot_has_1_space_left_and_second_parking_lot_capacity_has_2_spaces_left(){

        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        smartParkingBoy.park(new Car());

        Assert.assertEquals(1, firstParkingLot.getRemainPosition());
        Assert.assertEquals(1, secondParkingLot.getRemainPosition());
    }

}
