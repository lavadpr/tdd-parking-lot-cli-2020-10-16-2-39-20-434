package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparing(ParkingLot::getCurrentCapacity)).get();
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicket != null){
            for (ParkingLot parkingLot : parkingLots) {
                try {
                    return parkingLot.fetch(parkingTicket);
                } catch (ParkingException e) {
                    e.printStackTrace();
                }
            }
            return null;
        } else throw new ParkingException("Please provide your parking ticket");
    }
}
