package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));

        //when
        ParkingTicket ticket = parkingBoy.park(car);

        //then
        assertNotNull(ticket);
    }
    @Test
    void should_return_correct_car_when_fetching_given_a_correct_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
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
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(2));
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
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        ParkingTicket parkingTicket = new ParkingTicket();

        //when
        //then
        assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetch(parkingTicket);
        });
    }
    @Test
    void should_no_car_when_fetching_given_used_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        //when
        Car fetchedSameCar = parkingBoy.fetch(parkingTicket);

        //then
        assertNull(fetchedSameCar);
    }
    @Test
    void should_no_car_when_fetching_given_no_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));

        //when
        Car fetchedCar = parkingBoy.fetch(null);

        //then
        assertNull(fetchedCar);
    }
    @Test
    void should_no_ticket_when_parking_given_1_parking_lot_capacity_and_park() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        //when
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        //then
        assertNull(parkingTicket2);
    }
    @Test
    void should_1_parking_lot_capacity_when_parking_and_fetching_given_1_parking_lot_capacity() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        //then
        assertEquals(1, parkingLot.getCapacity());
    }
}
