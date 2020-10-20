package com.oocl.cultivation;

import com.oocl.exception.ParkingException;
import com.oocl.exception.ParkingExceptionMessages;

import java.util.List;

public class ServiceManager extends ParkingBoy{
    private List<ParkingBoy> parkingBoys;

    public ServiceManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
        parkingBoys.add(new ParkingBoy(parkingLots));
    }

    public ServiceManager(List<ParkingBoy> parkingBoys) {
        super(null);
        this.parkingBoys = parkingBoys;
    }

    public ParkingTicket park(ParkingBoy parkingBoy, Car car) {
        throwExceptionIfParkingBoyNotInList(parkingBoy);
        return parkingBoy.park(car);
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public void addParkingBoyInList(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
    }

    public Car fetch(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        throwExceptionIfParkingBoyNotInList(parkingBoy);
        return parkingBoy.fetch(parkingTicket);
    }

    private void throwExceptionIfParkingBoyNotInList(ParkingBoy parkingBoy){
        boolean isParkingBoyInManagementList = parkingBoys.stream().anyMatch(currentParkingBoy -> currentParkingBoy == parkingBoy);
        if(!isParkingBoyInManagementList) {
            throw new ParkingException(ParkingExceptionMessages.NO_PARKING_BOY_IN_MANAGEMENT_LIST);
        }
    }
}
