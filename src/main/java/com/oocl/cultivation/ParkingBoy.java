package com.oocl.cultivation;

import com.oocl.exception.ParkingException;
import com.oocl.exception.ParkingExceptionMessages;

import java.util.List;
import java.util.function.Predicate;

public class ParkingBoy {
    List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        if (parkingLots != null) {
            ParkingTicket parkingTicket = parkInGivenParkingLot(
                    getParkingLot(currentParkingLot -> currentParkingLot.getCurrentCapacity() != 0), car);
            return parkingTicket;
        }
        throw new ParkingException(ParkingExceptionMessages.NO_PARKING_LOT_FROM_PARKING_BOY);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket != null) {
            ParkingLot parkingLot =
                    getParkingLot(currentParkingLot -> currentParkingLot.getTicketCarMap().get(parkingTicket) != null);
            if (parkingLot != null) {
                return parkingLot.fetch(parkingTicket);
            }
            throw new ParkingException(ParkingExceptionMessages.UNRECOGNIZED_PARKING_TICKET);
        }
        throw new ParkingException(ParkingExceptionMessages.NO_PARKING_TICKET);
    }

    ParkingTicket parkInGivenParkingLot(ParkingLot parkingLot, Car car) {
        if (parkingLot != null && parkingLot.getCurrentCapacity() != 0) {
            return parkingLot.park(car);
        }
        throw new ParkingException(ParkingExceptionMessages.FULL_PARKING_LOTS);
    }

    private ParkingLot getParkingLot(Predicate<ParkingLot> parkingLot) {
        return parkingLots.stream()
                .filter(parkingLot)
                .findFirst()
                .orElse(null);
    }
}
