package com.oocl;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(){
        super("Please provide your parking ticket.");
    }
}
