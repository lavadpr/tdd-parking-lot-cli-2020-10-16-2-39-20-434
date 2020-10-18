package com.oocl.cultivation;

import java.util.List;

public class ServiceManager extends ParkingBoy{
    public List<ParkingBoy> parkingBoys;

    public ServiceManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
        parkingBoys.add(new ParkingBoy(parkingLots));
    }


    public ParkingTicket park(ParkingBoy parkingBoy, Car car) {
        if(parkingBoys.stream().anyMatch(currentParkingBoy -> currentParkingBoy == parkingBoy)){
            return parkingBoy.park(car);
        }
        throw new ParkingException("Parking boy not in management list");
    }

    public Car fetch(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        return null;
    }
}
