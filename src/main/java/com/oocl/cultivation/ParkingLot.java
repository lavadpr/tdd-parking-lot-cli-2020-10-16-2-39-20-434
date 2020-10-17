package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        if(capacity != 0){
            ticketCarMap.put(ticket, car);
            capacity--;
            return ticket;
        } else throw new ParkingException("Not enough position.");
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicket != null) {
            Car car = ticketCarMap.get(parkingTicket);
            if (car != null) {
                ticketCarMap.remove(parkingTicket);
                capacity++;
                return car;
            } else throw new ParkingException("Unrecognized Parking Ticket");
        } else throw new ParkingException("Please provide your parking ticket");

    }
}
