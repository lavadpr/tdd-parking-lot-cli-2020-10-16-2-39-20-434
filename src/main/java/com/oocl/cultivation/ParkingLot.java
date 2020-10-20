package com.oocl.cultivation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private Map<ParkingTicket, Car> ticketCarMap = new ConcurrentHashMap<>();
    private final int totalCapacity;
    private static final int DEFAULT_CAPACITY = 10;

    public ParkingLot(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public ParkingLot() {
        totalCapacity = DEFAULT_CAPACITY;
    }

    public int getCurrentCapacity() {
        return totalCapacity - ticketCarMap.size();
    }

    double getCurrentTotalRatio() {
        return (double) getCurrentCapacity() / (double) totalCapacity;
    }

    Map<ParkingTicket, Car> getTicketCarMap() {
        return ticketCarMap;
    }

    ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    Car fetch(ParkingTicket parkingTicket) {
        Car car = ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        return car;
    }
}
