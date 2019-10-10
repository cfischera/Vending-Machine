package com.tsg.fischer.vending_machine_spring.dto;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Vendible {

    private String slotID, itemName;
    private BigDecimal itemPrice;
    private int itemQuantity;

    public Vendible(String slotID, String itemName, String itemPrice, String itemQuantity) {
        this.slotID = slotID;
        this.itemName = itemName;
        this.itemPrice = new BigDecimal(itemPrice);
        this.itemQuantity = Integer.parseInt(itemQuantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendible vendible = (Vendible) o;
        return itemQuantity == vendible.itemQuantity &&
                slotID.equals(vendible.slotID) &&
                itemName.equals(vendible.itemName) &&
                itemPrice.equals(vendible.itemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotID, itemName, itemPrice, itemQuantity);
    }

    public String getSlotID() {
        return this.slotID;
    }

    public String getItemName() {
        return this.itemName;
    }

    public BigDecimal getItemPrice() {
        return this.itemPrice;
    }

    public int getQuantity() {
        return this.itemQuantity;
    }
}
