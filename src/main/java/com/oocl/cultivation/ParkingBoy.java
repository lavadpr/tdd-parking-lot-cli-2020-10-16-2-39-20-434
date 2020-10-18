package com.oocl.cultivation;

import java.util.List;
import java.util.function.Predicate;

public class ParkingBoy {
    List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        if (parkingLots != null) {
            return parkWithFullException(
                    getParkingLot(currentParkingLot -> currentParkingLot.getCurrentCapacity() != 0), car);
        }
        throw new ParkingException("Parking boy has no parking lot");
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket != null) {
            ParkingLot parkingLot =
                    getParkingLot(currentParkingLot -> currentParkingLot.getTicketCarMap().get(parkingTicket) != null);
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

    private ParkingLot getParkingLot(Predicate<ParkingLot> parkingLot) {
        return parkingLots.stream()
                .filter(parkingLot)
                .findFirst()
                .orElse(null);
    }
}
