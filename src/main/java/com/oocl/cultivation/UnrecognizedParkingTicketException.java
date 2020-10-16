package com.oocl.cultivation;

public class UnrecognizedParkingTicketException extends RuntimeException{
    UnrecognizedParkingTicketException(String message){
        super(message);
    }
}
