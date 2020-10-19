package com.oocl.cultivation;

import com.oocl.exception.ParkingException;
import com.oocl.exception.ParkingExceptionMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceManagerTest {
    private Car car;
    private ParkingLot parkingLotWith10Capacity;
    private ParkingLot parkingLotWith5Capacity;
    private ParkingLot parkingLotWith1Capacity;
    private List<ParkingLot> parkingLots;
    private List<ParkingBoy> parkingBoyList;
    private ParkingBoy parkingBoy;
    private SmartParkingBoy smartParkingBoy;
    private SuperSmartParkingBoy superSmartParkingBoy;

    @BeforeEach
    void setUp(){
        car = new Car();
        parkingLotWith10Capacity = new ParkingLot();
        parkingLotWith5Capacity = new ParkingLot(5);
        parkingLotWith1Capacity = new ParkingLot(1);
        parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotWith1Capacity);
        List<ParkingLot> smartParkingLots = new LinkedList<>();
        smartParkingLots.add(parkingLotWith5Capacity);
        smartParkingLots.add(parkingLotWith1Capacity);
        List<ParkingLot> superSmartParkingLots = new LinkedList<>();
        superSmartParkingLots.add(parkingLotWith10Capacity);
        superSmartParkingLots.add(parkingLotWith5Capacity);
        superSmartParkingLots.add(parkingLotWith1Capacity);
        parkingBoy = new ParkingBoy(parkingLots);
        smartParkingBoy = new SmartParkingBoy(smartParkingLots);
        superSmartParkingBoy = new SuperSmartParkingBoy(superSmartParkingLots);
        parkingBoyList = new LinkedList<>();
        parkingBoyList.add(parkingBoy);
        parkingBoyList.add(smartParkingBoy);
        parkingBoyList.add(superSmartParkingBoy);
    }
    @Test
    void should_throw_no_parking_lot_when_parking_given_service_manager_not_parking_boy() {
        //given
        ServiceManager serviceManager = new ServiceManager(null, parkingBoyList);

        //when
        //then
        assertEquals(ParkingExceptionMessages.NO_PARKING_LOT_FROM_PARKING_BOY,
                assertThrows(ParkingException.class, () -> {
                    serviceManager.park(car);
                }).getMessage());
    }

    @Test
    void should_current_capacity_when_parking_and_fetching_given_service_manager_not_parking_boy() {
        //given
        ServiceManager serviceManager = new ServiceManager(null, parkingBoyList);

        //when
        serviceManager.fetch(parkingBoy, serviceManager.park(parkingBoy, car));
        serviceManager.park(smartParkingBoy, car);
        serviceManager.park(superSmartParkingBoy, car);
        serviceManager.park(smartParkingBoy, car);
        serviceManager.park(superSmartParkingBoy, car);

        //then
        assertEquals(9, parkingLotWith10Capacity.getCurrentCapacity());
        assertEquals(3, parkingLotWith5Capacity.getCurrentCapacity());
        assertEquals(0, parkingLotWith1Capacity.getCurrentCapacity());
    }

    @Test
    void should_current_capacity_when_parking_and_fetching_given_service_manager_parking_boy() {
        //given
        ServiceManager serviceManager = new ServiceManager(parkingLots, parkingBoyList);

        //when
        serviceManager.fetch(parkingBoy, serviceManager.park(car));
        serviceManager.park(smartParkingBoy, car);
        serviceManager.park(superSmartParkingBoy, car);
        serviceManager.park(smartParkingBoy, car);
        serviceManager.park(superSmartParkingBoy, car);

        //then
        assertEquals(9, parkingLotWith10Capacity.getCurrentCapacity());
        assertEquals(3, parkingLotWith5Capacity.getCurrentCapacity());
        assertEquals(0, parkingLotWith1Capacity.getCurrentCapacity());
    }
}
