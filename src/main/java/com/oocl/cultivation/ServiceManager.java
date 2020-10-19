package com.oocl.cultivation;

import com.oocl.exception.ParkingException;

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
        boolean isParkingBoyInManagementList = parkingBoys.stream().anyMatch(currentParkingBoy -> currentParkingBoy == parkingBoy);
        if(!isParkingBoyInManagementList)
            throw new ParkingException("Parking boy not in management list");
    }
}
