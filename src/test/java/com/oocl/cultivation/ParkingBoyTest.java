package com.oocl.cultivation;

import com.oocl.exception.ParkingException;
import com.oocl.exception.ParkingExceptionMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    private Car firstCar;
    private Car firstFetchedCar;
    private ParkingLot parkingLotWith10Capacity;
    private ParkingLot parkingLotWith1Capacity;
    private List<ParkingLot> parkingLotsWithLotWith10Capacity;
    private List<ParkingLot> parkingLotsWithLotWith1Capacity;
    private List<ParkingLot> parkingLots;
    private ParkingBoy parkingBoyFor10CapacityLot;
    private ParkingBoy parkingBoyFor1CapacityLot;
    private ParkingBoy parkingBoy;
    private ParkingTicket parkingTicket;

    @BeforeEach
    void setup(){
        firstCar = new Car();
        parkingLotWith10Capacity = new ParkingLot();
        parkingLotWith1Capacity = new ParkingLot(1);
        parkingLotsWithLotWith10Capacity = new LinkedList<>();
        parkingLotsWithLotWith10Capacity.add(parkingLotWith10Capacity);
        parkingBoyFor10CapacityLot = new ParkingBoy(parkingLotsWithLotWith10Capacity);
        parkingTicket = parkingBoyFor10CapacityLot.park(firstCar);
        firstFetchedCar = parkingBoyFor10CapacityLot.fetch(parkingTicket);
        parkingLotsWithLotWith1Capacity = new LinkedList<>();
        parkingLotsWithLotWith1Capacity.add(parkingLotWith1Capacity);
        parkingBoyFor1CapacityLot = new ParkingBoy(parkingLotsWithLotWith1Capacity);
        parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotWith1Capacity);
        parkingLots.add(parkingLotWith10Capacity);
        parkingBoy = new ParkingBoy(parkingLots);
    }

    @Test
    void should_return_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        //when
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_correct_car_when_fetching_given_a_correct_ticket() {
        //given
        //when
        //then
        assertSame(firstCar, firstFetchedCar);
    }

    @Test
    void should_return_correct_two_cars_when_fetching_given_two_cars_to_parking_boy_and_park() {
        //given
        Car secondCar = new Car();
        ParkingTicket secondParkingTicket = parkingBoyFor10CapacityLot.park(secondCar);

        //when
        //then
        assertSame(firstCar, firstFetchedCar);
        assertSame(secondCar, parkingBoyFor10CapacityLot.fetch(secondParkingTicket));
    }

    @Test
    void should_no_car_and_unrecognized_parking_ticket_when_fetching_given_wrong_ticket() {
        //given
        //when
        //then
        assertEquals(ParkingExceptionMessages.UNRECOGNIZED_PARKING_TICKET,
                assertThrows(ParkingException.class, () -> {
                    parkingBoyFor10CapacityLot.fetch(parkingTicket);
                }).getMessage());
    }

    @Test
    void should_no_car_when_fetching_given_used_ticket() {
        //given
        //when
        //then
        assertEquals(ParkingExceptionMessages.UNRECOGNIZED_PARKING_TICKET,
                assertThrows(ParkingException.class, () -> {
                    parkingBoyFor10CapacityLot.fetch(parkingTicket);
                }).getMessage());
    }

    @Test
    void should_no_car_when_fetching_given_no_ticket() {
        //given
        //when
        //then
        assertEquals(ParkingExceptionMessages.NO_PARKING_TICKET,
                assertThrows(ParkingException.class, () -> {
                    parkingBoyFor10CapacityLot.fetch(null);
                }).getMessage());
    }

    @Test
    void should_no_ticket_when_parking_given_1_parking_lot_capacity_and_park() {
        //given
        parkingBoyFor1CapacityLot.park(firstCar);

        //when
        //then
        assertEquals(ParkingExceptionMessages.FULL_PARKING_LOTS,
                assertThrows(ParkingException.class, () -> {
                    parkingBoyFor1CapacityLot.park(firstCar);
                }).getMessage());
    }

    @Test
    void should_1_parking_lot_capacity_when_parking_and_fetching_given_1_parking_lot_capacity() {
        //given
        //when
        parkingBoyFor1CapacityLot.fetch(parkingBoyFor1CapacityLot.park(firstCar));

        //then
        assertEquals(1, parkingLotWith1Capacity.getCurrentCapacity());
    }

    @Test
    void should_correct_current_capacity_on_parking_lots_when_parking_2_times_given_2_parking_lots() {
        //given
        //when
        parkingBoy.park(firstCar);
        parkingBoy.park(firstCar);

        //then
        assertEquals(0, parkingLotWith1Capacity.getCurrentCapacity());
        assertEquals(9, parkingLotWith10Capacity.getCurrentCapacity());
    }

    @Test
    void should_correct_current_capacity_on_parking_lots_when_parking_and_fetching_given_2_parking_lots() {
        //given
        //when
        parkingBoy.park(firstCar);
        parkingBoy.fetch(parkingBoy.park(firstCar));
        parkingBoy.park(firstCar);
        parkingBoy.fetch(parkingBoy.park(firstCar));

        //then
        assertEquals(0, parkingLotWith1Capacity.getCurrentCapacity());
        assertEquals(9, parkingLotWith10Capacity.getCurrentCapacity());
    }
}
