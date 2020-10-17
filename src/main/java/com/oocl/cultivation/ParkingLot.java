package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    private int currentCapacity;
    private final int totalCapacity;

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public ParkingLot(int currentCapacity) {
        this.currentCapacity = currentCapacity;
        totalCapacity = currentCapacity;
    }

    public ParkingLot() {
        currentCapacity = 10;
        totalCapacity = currentCapacity;
    }


    ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        if (currentCapacity != 0) {
            ticketCarMap.put(ticket, car);
            currentCapacity--;
            return ticket;
        } else throw new ParkingException("Not enough position.");
    }

    Car fetch(ParkingTicket parkingTicket) {
        Car car = ticketCarMap.get(parkingTicket);
        if (car != null) {
            ticketCarMap.remove(parkingTicket);
            currentCapacity++;
            return car;
        } else throw new ParkingException("Unrecognized Parking Ticket");
    }

}
