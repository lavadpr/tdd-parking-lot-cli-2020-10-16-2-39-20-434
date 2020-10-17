package com.oocl.cultivation;

import net.bytebuddy.dynamic.scaffold.MethodGraph;
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
        ParkingTicket ticket = parkingBoy.park(0,car);

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
        ParkingTicket parkingTicket = parkingBoy.park(0,car);

        //when
        Car fetchedCar = parkingBoy.fetch(0,parkingTicket);

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
        ParkingTicket parkingTicket1 = parkingBoy.park(0,car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(0,car2);

        //when
        Car fetchedCar1 = parkingBoy.fetch(0,parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(0,parkingTicket2);

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
            parkingBoy.fetch(0,parkingTicket);
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
        ParkingTicket parkingTicket = parkingBoy.park(0,car);
        Car fetchedCar = parkingBoy.fetch(0,parkingTicket);

        //when
        //then
        assertEquals("Unrecognized Parking Ticket",assertThrows(ParkingException.class, () -> {
            parkingBoy.fetch(0,parkingTicket);
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
            parkingBoy.fetch(0,null);
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
        ParkingTicket parkingTicket1 = parkingBoy.park(0,car1);
        //when
        //then
        assertEquals("Not enough position.",assertThrows(ParkingException.class, () -> {
            parkingBoy.park(0,car2);
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
        ParkingTicket parkingTicket = parkingBoy.park(0,car);
        Car fetchedCar = parkingBoy.fetch(0,parkingTicket);

        //then
        assertEquals(1, parkingLot.getCapacity());
    }
}
