package com.tsg.fischer.vending_machine_spring.service;

public class VendingNoItemInventoryException extends Exception {
    public VendingNoItemInventoryException(String msg) {
        super(msg);
    }

    public VendingNoItemInventoryException(String msg, Throwable cause) {
        super(msg, cause);
    }
}