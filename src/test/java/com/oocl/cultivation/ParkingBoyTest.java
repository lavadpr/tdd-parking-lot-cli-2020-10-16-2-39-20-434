package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        //when
        ParkingTicket ticket = parkingBoy.park(car);

        //then
        assertNotNull(ticket);
    }
//    @Test
//    void should_return_correct_car_when_fetching_given_a_correct_ticket() {
//        //given
//        Car car = new Car();
//        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//
//        //when
//        ParkingTicket ticket = parkingBoy.park();
//
//        //then
//        assertNotNull(ticket);
//    }
}
