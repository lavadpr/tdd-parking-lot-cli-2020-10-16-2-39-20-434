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
        }
        return null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car =  ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        return car;
    }
}
