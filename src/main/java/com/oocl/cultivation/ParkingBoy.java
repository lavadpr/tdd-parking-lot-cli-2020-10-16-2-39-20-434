package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    private int currentParkingLot;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        currentParkingLot = 0;
    }

    public ParkingTicket park(Car car) {
        if(parkingLots.get(currentParkingLot).isFull() && currentParkingLot != parkingLots.size()-1){
            currentParkingLot++;
        }
        return parkingLots.get(currentParkingLot).park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        ParkingLot parkingLot = parkingLots.get(currentParkingLot);
        if(parkingLot.isEmpty() && currentParkingLot != 0){
            currentParkingLot--;
        }
        return parkingLot.fetch(parkingTicket);
    }
}
