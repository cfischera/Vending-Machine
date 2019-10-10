package com.tsg.fischer.vending_machine_spring.dto;

public class Beverage extends Vendible {
    public Beverage(String slotID, String itemName, String itemPrice, String itemQuantity) {
        super(slotID, itemName, itemPrice, itemQuantity);
    }
}