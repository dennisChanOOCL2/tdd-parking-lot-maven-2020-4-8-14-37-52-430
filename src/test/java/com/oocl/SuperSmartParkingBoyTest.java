package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class SuperSmartParkingBoyTest {

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
    public void smart_parking_boyshould_park_car_to_first_parking_lot_when_first_parking_lot_capacity_is_2_has_1_space_left_and_second_parking_lot_capacity_is_5_has_2_spaces_left(){

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

    @Test
    public void smart_parking_boyshould_park_car_to_second_parking_lot_when_first_parking_lot_capacity_is_5_has_2_space_left_and_second_parking_lot_capacity_is_2_has_1_spaces_left(){

        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(2);

        ParkingBoy parkingBoyForFirstParkingLot = new ParkingBoy(firstParkingLot);
        ParkingBoy parkingBoyForSecondParkingLot = new ParkingBoy(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(firstParkingLot, secondParkingLot);

        parkingBoyForSecondParkingLot.park(new Car());
        parkingBoyForFirstParkingLot.park(new Car());
        parkingBoyForFirstParkingLot.park(new Car());
        parkingBoyForFirstParkingLot.park(new Car());

        superSmartParkingBoy.park(new Car());

        Assert.assertEquals(2, firstParkingLot.getRemainPosition());
        Assert.assertEquals(0, secondParkingLot.getRemainPosition());

    }

}
