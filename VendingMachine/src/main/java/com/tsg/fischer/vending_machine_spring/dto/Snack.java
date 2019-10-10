package com.tsg.fischer.vending_machine_spring.dto;

public class Snack extends Vendible {
    public Snack(String slotID, String itemName, String itemPrice, String itemQuantity) {
        super(slotID, itemName, itemPrice, itemQuantity);
    }
}