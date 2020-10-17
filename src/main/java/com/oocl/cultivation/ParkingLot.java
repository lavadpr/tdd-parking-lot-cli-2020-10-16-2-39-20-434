package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    private int currentCapacity;
    private final int totalCapacity;

    public Map<ParkingTicket, Car> getTicketCarMap() {
        return ticketCarMap;
    }

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
