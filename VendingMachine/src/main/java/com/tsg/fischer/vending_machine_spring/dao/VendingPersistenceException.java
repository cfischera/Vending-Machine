package com.tsg.fischer.vending_machine_spring.dao;

public class VendingPersistenceException extends Exception {

    public VendingPersistenceException(String msg) {
        super(msg);
    }

    public VendingPersistenceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

