package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        ParkingTicket ticket = parkingBoy.park(car);

        //then
        assertNotNull(ticket);
    }
    @Test
    void should_return_correct_car_when_fetching_given_a_correct_ticket() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchedCar);
    }
    @Test
    void should_return_correct_two_cars_when_fetching_given_two_cars_to_parking_boy_and_park() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        //when
        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);

        //then
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }
    @Test
    void should_no_car_and_unrecognized_parking_ticket_when_fetching_given_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket parkingTicket = new ParkingTicket();
        //when
        //then
        assertEquals("Unrecognized Parking Ticket",assertThrows(ParkingException.class, () -> {
            parkingBoy.fetch(parkingTicket);
        }).getMessage());
    }
    @Test
    void should_no_car_when_fetching_given_used_ticket() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        //when
        //then
        assertEquals("Unrecognized Parking Ticket",assertThrows(ParkingException.class, () -> {
            parkingBoy.fetch(parkingTicket);
        }).getMessage());
    }
    @Test
    void should_no_car_when_fetching_given_no_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        //then
        assertEquals("Please provide your parking ticket",assertThrows(ParkingException.class, () -> {
            parkingBoy.fetch(null);
        }).getMessage());
    }
    @Test
    void should_no_ticket_when_parking_given_1_parking_lot_capacity_and_park() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        //when
        //then
        assertEquals("Not enough position.",assertThrows(ParkingException.class, () -> {
            parkingBoy.park(car2);
        }).getMessage());
    }
    @Test
    void should_1_parking_lot_capacity_when_parking_and_fetching_given_1_parking_lot_capacity() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        //then
        assertEquals(1, parkingLot.getCurrentCapacity());
    }
    @Test
    void should_8_and_10_current_capacity_on_parking_lots_when_parking_2_times_given_2_parking_lots() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        //then
        assertEquals(8, parkingLot1.getCurrentCapacity());
        assertEquals(10, parkingLot2.getCurrentCapacity());
    }
}
