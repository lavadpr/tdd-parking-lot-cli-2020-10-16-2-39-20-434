package com.oocl.cultivation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private Map<ParkingTicket, Car> ticketCarMap = new ConcurrentHashMap<>();
    private int currentCapacity;
    private final int totalCapacity;

    public ParkingLot(int currentCapacity) {
        this.currentCapacity = currentCapacity;
        totalCapacity = currentCapacity;
    }

    public ParkingLot() {
        currentCapacity = 10;
        totalCapacity = currentCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    double getCurrentTotalRatio() {
        return (double) currentCapacity / (double) totalCapacity;
    }

    Map<ParkingTicket, Car> getTicketCarMap() {
        return ticketCarMap;
    }

    ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        ticketCarMap.put(ticket, car);
        currentCapacity--;
        return ticket;
    }

    Car fetch(ParkingTicket parkingTicket) {
        Car car = ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        currentCapacity++;
        return car;
    }
}
