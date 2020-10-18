package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        if(parkingLots != null) {
            ParkingLot parkingLot = parkingLots.stream()
                    .filter(currentParkingLot -> currentParkingLot.getCurrentCapacity() != 0)
                    .findFirst()
                    .orElse(null);
            return parkWithFullException(parkingLot, car);
        }
        throw new ParkingException("Parking boy has no parking lot");
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket != null) {
            ParkingLot parkingLot = parkingLots.stream()
                    .filter(currentParkingLot -> currentParkingLot.getTicketCarMap().get(parkingTicket) != null)
                    .findFirst()
                    .orElse(null);
            if (parkingLot != null) {
                return parkingLot.fetch(parkingTicket);
            }
            throw new ParkingException("Unrecognized Parking Ticket");
        }
        throw new ParkingException("Please provide your parking ticket");
    }

    ParkingTicket parkWithFullException(ParkingLot parkingLot, Car car) {
        if (parkingLot != null) {
            if (parkingLot.getCurrentCapacity() != 0) {
                return parkingLot.park(car);
            }
        }
        throw new ParkingException("Not enough position.");
    }
}
