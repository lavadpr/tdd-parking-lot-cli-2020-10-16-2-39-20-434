package com.oocl.cultivation;

import java.util.List;

public class ServiceManager extends ParkingBoy{
    private List<ParkingBoy> parkingBoys;

    public ServiceManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
        parkingBoys.add(new ParkingBoy(parkingLots));
    }

    public ParkingTicket park(ParkingBoy parkingBoy, Car car) {
        throwExceptionIfParkingBoyNotInList(parkingBoy);
        return parkingBoy.park(car);
    }

    public Car fetch(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        throwExceptionIfParkingBoyNotInList(parkingBoy);
        return parkingBoy.fetch(parkingTicket);
    }

    private void throwExceptionIfParkingBoyNotInList(ParkingBoy parkingBoy){
        if(parkingBoys.stream().noneMatch(currentParkingBoy -> currentParkingBoy == parkingBoy))
            throw new ParkingException("Parking boy not in management list");
    }
}
