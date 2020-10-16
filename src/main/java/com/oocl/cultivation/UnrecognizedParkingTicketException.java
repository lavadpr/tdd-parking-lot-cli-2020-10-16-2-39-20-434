package com.oocl.cultivation;

public class UnrecognizedParkingTicketException extends RuntimeException{
    UnrecognizedParkingTicketException(){
        super("Unrecognized Parking Ticket");
    }
}
