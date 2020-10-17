package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    public List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if(parkingLot.getCurrentCapacity() != 0) {
                return parkingLot.park(car);
            }
        }
        throw new ParkingException("Not enough position.");
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicket != null){
            for (ParkingLot parkingLot : parkingLots) {
                if(parkingLot.getTicketCarMap().get(parkingTicket) != null) {
                    return parkingLot.fetch(parkingTicket);
                }
            }
            throw new ParkingException("Unrecognized Parking Ticket");
        } else throw new ParkingException("Please provide your parking ticket");
    }
}
