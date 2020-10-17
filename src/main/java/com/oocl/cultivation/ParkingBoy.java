package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLot;

    public ParkingBoy(List<ParkingLot> parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(int parkingLotNumber, Car car) {
        return parkingLot.get(parkingLotNumber).park(car);
    }

    public Car fetch(int parkingLotnumber, ParkingTicket parkingTicket) {
        return parkingLot.get(parkingLotnumber).fetch(parkingTicket);
    }
}
