package com.oocl.cultivation;

import com.oocl.exception.ParkingException;
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
        assertEquals("Unrecognized Parking Ticket", assertThrows(ParkingException.class, () -> {
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
        assertEquals("Unrecognized Parking Ticket", assertThrows(ParkingException.class, () -> {
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
        assertEquals("Please provide your parking ticket", assertThrows(ParkingException.class, () -> {
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
        assertEquals("Not enough position.", assertThrows(ParkingException.class, () -> {
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

    @Test
    void should_0_and_8_current_capacity_on_parking_lots_when_parking_given_2_parking_lots() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();
        Car car7 = new Car();
        Car car8 = new Car();
        Car car9 = new Car();
        Car car10 = new Car();
        Car car11 = new Car();
        Car car12 = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        ParkingTicket parkingTicket3 = parkingBoy.park(car3);
        ParkingTicket parkingTicket4 = parkingBoy.park(car4);
        ParkingTicket parkingTicket5 = parkingBoy.park(car5);
        ParkingTicket parkingTicket6 = parkingBoy.park(car6);
        ParkingTicket parkingTicket7 = parkingBoy.park(car7);
        ParkingTicket parkingTicket8 = parkingBoy.park(car8);
        ParkingTicket parkingTicket9 = parkingBoy.park(car9);
        ParkingTicket parkingTicket10 = parkingBoy.park(car10);
        ParkingTicket parkingTicket11 = parkingBoy.park(car11);
        ParkingTicket parkingTicket12 = parkingBoy.park(car12);

        //then
        assertEquals(0, parkingLot1.getCurrentCapacity());
        assertEquals(8, parkingLot2.getCurrentCapacity());
    }

    @Test
    void should_0_and_9_current_capacity_on_parking_lots_when_parking_and_fetching_given_2_parking_lots() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();
        Car car7 = new Car();
        Car car8 = new Car();
        Car car9 = new Car();
        Car car10 = new Car();
        Car car11 = new Car();
        Car car12 = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        ParkingTicket parkingTicket3 = parkingBoy.park(car3);
        ParkingTicket parkingTicket4 = parkingBoy.park(car4);
        ParkingTicket parkingTicket5 = parkingBoy.park(car5);
        ParkingTicket parkingTicket6 = parkingBoy.park(car6);
        ParkingTicket parkingTicket7 = parkingBoy.park(car7);
        ParkingTicket parkingTicket8 = parkingBoy.park(car8);
        ParkingTicket parkingTicket9 = parkingBoy.park(car9);
        ParkingTicket parkingTicket10 = parkingBoy.park(car10);
        ParkingTicket parkingTicket11 = parkingBoy.park(car11);
        ParkingTicket parkingTicket12 = parkingBoy.park(car12);
        car11 = parkingBoy.fetch(parkingTicket11);
        car1 = parkingBoy.fetch(parkingTicket1);
        ParkingTicket parkingTicket13 = parkingBoy.park(car1);
        car12 = parkingBoy.fetch(parkingTicket12);
        ParkingTicket parkingTicket14 = parkingBoy.park(car12);

        //then
        assertEquals(0, parkingLot1.getCurrentCapacity());
        assertEquals(9, parkingLot2.getCurrentCapacity());
    }

    @Test
    void should_0_0_0_current_capacity_on_parking_lots_when_parking_given_3_parking_lots_and_smart() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();

        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        ParkingTicket parkingTicket1 = parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(car);
        ParkingTicket parkingTicket3 = parkingBoy.park(car);
        ParkingTicket parkingTicket4 = parkingBoy.park(car);
        ParkingTicket parkingTicket5 = parkingBoy.park(car);
        ParkingTicket parkingTicket6 = parkingBoy.park(car);
        ParkingTicket parkingTicket7 = parkingBoy.park(car);
        ParkingTicket parkingTicket8 = parkingBoy.park(car);
        ParkingTicket parkingTicket9 = parkingBoy.park(car);
        ParkingTicket parkingTicket10 = parkingBoy.park(car);
        ParkingTicket parkingTicket11 = parkingBoy.park(car);
        ParkingTicket parkingTicket12 = parkingBoy.park(car);
        ParkingTicket parkingTicket13 = parkingBoy.park(car);
        ParkingTicket parkingTicket14 = parkingBoy.park(car);
        ParkingTicket parkingTicket15 = parkingBoy.park(car);
        ParkingTicket parkingTicket16 = parkingBoy.park(car);
        ParkingTicket parkingTicket17 = parkingBoy.park(car);
        ParkingTicket parkingTicket18 = parkingBoy.park(car);
        ParkingTicket parkingTicket19 = parkingBoy.park(car);
        ParkingTicket parkingTicket20 = parkingBoy.park(car);
        ParkingTicket parkingTicket21 = parkingBoy.park(car);
        ParkingTicket parkingTicket22 = parkingBoy.park(car);
        ParkingTicket parkingTicket23 = parkingBoy.park(car);
        ParkingTicket parkingTicket24 = parkingBoy.park(car);
        ParkingTicket parkingTicket25 = parkingBoy.park(car);
        ParkingTicket parkingTicket26 = parkingBoy.park(car);
        ParkingTicket parkingTicket27 = parkingBoy.park(car);
        ParkingTicket parkingTicket28 = parkingBoy.park(car);
        ParkingTicket parkingTicket29 = parkingBoy.park(car);
        ParkingTicket parkingTicket30 = parkingBoy.park(car);


        //then
        assertEquals(0, parkingLot1.getCurrentCapacity());
        assertEquals(0, parkingLot2.getCurrentCapacity());
        assertEquals(0, parkingLot3.getCurrentCapacity());
    }

    @Test
    void should_0_0_current_capacity_on_parking_lots_when_parking_given_3_parking_lots_and_smart() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        ParkingTicket parkingTicket1 = parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(car);
        ParkingTicket parkingTicket3 = parkingBoy.park(car);
        ParkingTicket parkingTicket4 = parkingBoy.park(car);
        ParkingTicket parkingTicket5 = parkingBoy.park(car);
        ParkingTicket parkingTicket6 = parkingBoy.park(car);
        ParkingTicket parkingTicket7 = parkingBoy.park(car);
        ParkingTicket parkingTicket8 = parkingBoy.park(car);
        ParkingTicket parkingTicket9 = parkingBoy.park(car);
        ParkingTicket parkingTicket10 = parkingBoy.park(car);
        ParkingTicket parkingTicket11 = parkingBoy.park(car);
        ParkingTicket parkingTicket12 = parkingBoy.park(car);
        ParkingTicket parkingTicket13 = parkingBoy.park(car);
        ParkingTicket parkingTicket14 = parkingBoy.park(car);
        ParkingTicket parkingTicket15 = parkingBoy.park(car);
        ParkingTicket parkingTicket16 = parkingBoy.park(car);
        ParkingTicket parkingTicket17 = parkingBoy.park(car);
        ParkingTicket parkingTicket18 = parkingBoy.park(car);
        ParkingTicket parkingTicket19 = parkingBoy.park(car);
        ParkingTicket parkingTicket20 = parkingBoy.park(car);
        ParkingTicket parkingTicket21 = parkingBoy.park(car);
        ParkingTicket parkingTicket22 = parkingBoy.park(car);
        ParkingTicket parkingTicket23 = parkingBoy.park(car);
        ParkingTicket parkingTicket24 = parkingBoy.park(car);
        ParkingTicket parkingTicket25 = parkingBoy.park(car);
        ParkingTicket parkingTicket26 = parkingBoy.park(car);
        ParkingTicket parkingTicket27 = parkingBoy.park(car);
        ParkingTicket parkingTicket28 = parkingBoy.park(car);
        ParkingTicket parkingTicket29 = parkingBoy.park(car);
        ParkingTicket parkingTicket30 = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket30);
        parkingBoy.fetch(parkingTicket27);
        parkingBoy.fetch(parkingTicket1);
        ParkingTicket parkingTicket31 = parkingBoy.park(car);
        ParkingTicket parkingTicket32 = parkingBoy.park(car);

        //then
        assertEquals(0, parkingLot1.getCurrentCapacity());
        assertEquals(0, parkingLot2.getCurrentCapacity());
        assertEquals(1, parkingLot3.getCurrentCapacity());
    }

    @Test
    void should_correct_current_capacity_when_parking_given_3_parking_lots_and_super_smart() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(5);
        ParkingLot parkingLot3 = new ParkingLot(2);
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        ParkingTicket parkingTicket1 = parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(car);
        ParkingTicket parkingTicket3 = parkingBoy.park(car);
        ParkingTicket parkingTicket4 = parkingBoy.park(car);
        ParkingTicket parkingTicket5 = parkingBoy.park(car);
        ParkingTicket parkingTicket6 = parkingBoy.park(car);
        ParkingTicket parkingTicket7 = parkingBoy.park(car);
        ParkingTicket parkingTicket8 = parkingBoy.park(car);
        ParkingTicket parkingTicket9 = parkingBoy.park(car);
        ParkingTicket parkingTicket10 = parkingBoy.park(car);
        ParkingTicket parkingTicket11 = parkingBoy.park(car);
        ParkingTicket parkingTicket12 = parkingBoy.park(car);
        ParkingTicket parkingTicket13 = parkingBoy.park(car);
        ParkingTicket parkingTicket14 = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket1);
        parkingBoy.fetch(parkingTicket3);

        //then
        assertEquals(3, parkingLot1.getCurrentCapacity());
        assertEquals(1, parkingLot2.getCurrentCapacity());
        assertEquals(1, parkingLot3.getCurrentCapacity());
    }

    @Test
    void should_throw_no_parking_lot_when_parking__given_service_manager_not_parking_boy() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(5);
        ParkingLot parkingLot3 = new ParkingLot(2);
        List<ParkingLot> parkingLots1 = new LinkedList<>();
        parkingLots1.add(parkingLot1);
        parkingLots1.add(parkingLot2);
        parkingLots1.add(parkingLot3);
        List<ParkingLot> parkingLots2 = new LinkedList<>();
        parkingLots2.add(parkingLot2);
        parkingLots2.add(parkingLot3);
        List<ParkingLot> parkingLots3 = new LinkedList<>();
        parkingLots3.add(parkingLot1);
        SuperSmartParkingBoy parkingBoy1 = new SuperSmartParkingBoy(parkingLots1);
        SmartParkingBoy parkingBoy2 = new SmartParkingBoy(parkingLots2);
        ParkingBoy parkingBoy3 = new ParkingBoy(parkingLots3);
        List<ParkingBoy> parkingBoys = new LinkedList<>();
        parkingBoys.add(parkingBoy1);
        parkingBoys.add(parkingBoy2);
        parkingBoys.add(parkingBoy3);
        ServiceManager serviceManager = new ServiceManager(null, parkingBoys);

        //when
        //then
        assertEquals("Parking boy has no parking lot", assertThrows(ParkingException.class, () -> {
            serviceManager.park(car);
        }).getMessage());
    }

    @Test
    void should_current_capacity_when_parking_and_fetching_given_service_manager_not_parking_boy() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(5);
        ParkingLot parkingLot3 = new ParkingLot(2);

        List<ParkingLot> parkingLots1 = new LinkedList<>();
        parkingLots1.add(parkingLot1); //8
        parkingLots1.add(parkingLot2); //1
        parkingLots1.add(parkingLot3); //1

        List<ParkingLot> parkingLots2 = new LinkedList<>();
        parkingLots2.add(parkingLot2); //1
        parkingLots2.add(parkingLot3); //1

        List<ParkingLot> parkingLots3 = new LinkedList<>();
        parkingLots3.add(parkingLot1); //8

        SuperSmartParkingBoy parkingBoy1 = new SuperSmartParkingBoy(parkingLots1);
        SmartParkingBoy parkingBoy2 = new SmartParkingBoy(parkingLots2);
        ParkingBoy parkingBoy3 = new ParkingBoy(parkingLots3);

        List<ParkingBoy> parkingBoys = new LinkedList<>();
        parkingBoys.add(parkingBoy1);
        parkingBoys.add(parkingBoy2);
        parkingBoys.add(parkingBoy3);

        ServiceManager serviceManager = new ServiceManager(null, parkingBoys);

        //when
        ParkingTicket parkingTicket1 = serviceManager.park(parkingBoy1, car);
        ParkingTicket parkingTicket2 = serviceManager.park(parkingBoy2, car);
        ParkingTicket parkingTicket3 = serviceManager.park(parkingBoy3, car);
        ParkingTicket parkingTicket4 = serviceManager.park(parkingBoy2, car);
        ParkingTicket parkingTicket5 = serviceManager.park(parkingBoy1, car);
        ParkingTicket parkingTicket6 = serviceManager.park(parkingBoy2, car);
        ParkingTicket parkingTicket7 = serviceManager.park(parkingBoy2, car);
        car = serviceManager.fetch(parkingBoy2, parkingTicket2);

        //then
        assertEquals(8, parkingLot1.getCurrentCapacity());
        assertEquals(2, parkingLot2.getCurrentCapacity());
        assertEquals(1, parkingLot3.getCurrentCapacity());
    }

    @Test
    void should_current_capacity_when_parking_and_fetching_given_service_manager_parking_boy() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(5);
        ParkingLot parkingLot3 = new ParkingLot(2);

        List<ParkingLot> parkingLots1 = new LinkedList<>();
        parkingLots1.add(parkingLot1); //10
        parkingLots1.add(parkingLot2); //5
        parkingLots1.add(parkingLot3); //2

        List<ParkingLot> parkingLots2 = new LinkedList<>();
        parkingLots2.add(parkingLot2); //5
        parkingLots2.add(parkingLot3); //2

        List<ParkingLot> parkingLots3 = new LinkedList<>();
        parkingLots3.add(parkingLot1); //10

        List<ParkingLot> parkingLots4 = new LinkedList<>();
        parkingLots4.add(parkingLot3); //2

        SuperSmartParkingBoy parkingBoy1 = new SuperSmartParkingBoy(parkingLots1);
        SmartParkingBoy parkingBoy2 = new SmartParkingBoy(parkingLots2);
        ParkingBoy parkingBoy3 = new ParkingBoy(parkingLots3);

        List<ParkingBoy> parkingBoys = new LinkedList<>();
        parkingBoys.add(parkingBoy1);
        parkingBoys.add(parkingBoy2);
        parkingBoys.add(parkingBoy3);

        ServiceManager serviceManager = new ServiceManager(parkingLots4, parkingBoys);

        //when
        ParkingTicket parkingTicket1 = serviceManager.park(parkingBoy1, car);
        ParkingTicket parkingTicket2 = serviceManager.park(parkingBoy2, car);
        ParkingTicket parkingTicket3 = serviceManager.park(parkingBoy3, car);
        ParkingTicket parkingTicket4 = serviceManager.park(parkingBoy2, car);
        ParkingTicket parkingTicket5 = serviceManager.park(parkingBoy1, car);
        ParkingTicket parkingTicket6 = serviceManager.park(parkingBoy2, car);
        ParkingTicket parkingTicket7 = serviceManager.park(parkingBoy2, car);
        ParkingTicket parkingTicket8 = serviceManager.park(car);
        car = serviceManager.fetch(parkingTicket8);


        //then
        assertEquals(8, parkingLot1.getCurrentCapacity());
        assertEquals(1, parkingLot2.getCurrentCapacity());
        assertEquals(1, parkingLot3.getCurrentCapacity());
    }
}
