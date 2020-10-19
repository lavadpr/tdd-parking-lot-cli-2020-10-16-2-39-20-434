package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperSmartParkingBoyTest {
    private Car car;
    private ParkingLot parkingLotWith10Capacity;
    private ParkingLot parkingLotWith5Capacity;
    private ParkingLot parkingLotWith1Capacity;
    private List<ParkingLot> parkingLots;
    private SuperSmartParkingBoy parkingBoy;

    @BeforeEach
    void setUp(){
        car = new Car();
        parkingLotWith10Capacity = new ParkingLot();
        parkingLotWith5Capacity = new ParkingLot(5);
        parkingLotWith1Capacity = new ParkingLot(1);
        parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotWith10Capacity);
        parkingLots.add(parkingLotWith5Capacity);
        parkingLots.add(parkingLotWith1Capacity);
        parkingBoy = new SuperSmartParkingBoy(parkingLots);
    }
    @Test
    void should_correct_current_capacity_on_parking_lots_when_parking_given_3_parking_lots_and_super_smart() {
        //given
        //when
        parkingBoy.park(car);
        parkingBoy.park(car);
        parkingBoy.park(car);
        parkingBoy.park(car);
        parkingBoy.park(car);
        parkingBoy.park(car);
        parkingBoy.park(car);

        //then
        assertEquals(6, parkingLotWith10Capacity.getCurrentCapacity());
        assertEquals(3, parkingLotWith5Capacity.getCurrentCapacity());
        assertEquals(0, parkingLotWith1Capacity.getCurrentCapacity());
    }

    @Test
    void should_correct_current_capacity_on_parking_lots_when_parking_and_fetching_given_3_parking_lots_and_super_smart() {
        //given
        //when
        parkingBoy.park(car);
        parkingBoy.fetch(parkingBoy.park(car));
        parkingBoy.park(car);
        parkingBoy.park(car);
        parkingBoy.park(car);
        parkingBoy.park(car);
        parkingBoy.park(car);
        parkingBoy.fetch(parkingBoy.park(car));
        parkingBoy.park(car);

        //then
        assertEquals(6, parkingLotWith10Capacity.getCurrentCapacity());
        assertEquals(3, parkingLotWith5Capacity.getCurrentCapacity());
        assertEquals(0, parkingLotWith1Capacity.getCurrentCapacity());
    }
}
