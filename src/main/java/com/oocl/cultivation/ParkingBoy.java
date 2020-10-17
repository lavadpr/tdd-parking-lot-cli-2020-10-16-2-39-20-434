package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.park(car);
            } catch (ParkingException e) {
                e.printStackTrace();
            }
        }
        return null;
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
