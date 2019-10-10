package com.tsg.fischer.vending_machine_spring.service;

public class VendingInsufficientFundsException extends Exception {
    public VendingInsufficientFundsException(String msg) {
        super(msg);
    }

    public VendingInsufficientFundsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}