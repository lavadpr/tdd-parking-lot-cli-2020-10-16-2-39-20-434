package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparing(ParkingLot::getCurrentTotalRatio)).get();
        if(parkingLot.getCurrentCapacity() != 0) {
            return parkingLot.park(car);
        }
        throw new ParkingException("Not enough position.");
    }
}
