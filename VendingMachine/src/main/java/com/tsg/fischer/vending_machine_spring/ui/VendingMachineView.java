package com.tsg.fischer.vending_machine_spring.ui;

import com.tsg.fischer.vending_machine_spring.dto.ChangePurse;
import com.tsg.fischer.vending_machine_spring.dto.Vendible;

import java.util.List;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayInventoryBanner() {
        io.print("\nWhat would you like to eat?\n");
    }

    public void displayAllItems(List<Vendible> items) {
        io.print("Available Items:");
        for(Vendible item:items) {
            if(!(item.getQuantity() < 1)) {
                io.print(item.getSlotID() + " - " + item.getItemName() + " for $" + item.getItemPrice() + " " + item.getQuantity() + " left in stock");
            }
        }
    }

    public void displayErrorMessage(String msg) {
        io.print("Error: "+msg);
    }

    public int getAction() {
        return io.readInt("\nPress 1 to enter money.\n Press 2 to purchase an item.\n  Press 3 to exit.", 1, 3);
    }

    public void displayFarewell() {
        io.print("Thanks for vending!");
    }

    public double getUserMoney() {
        return io.readDouble("Please enter your amount of money: ");
    }

    public String getChoice() {
        return io.readString("Please enter your choice: ");
    }

    public void dispenseChange(ChangePurse change) {
        io.print("\nHere's your change:");
        io.print(""+change.getNumQuarters()+" quarters");
        io.print(""+change.getNumDimes()+" dimes");
        io.print(""+change.getNumNickels()+" nickels");
        io.print(""+change.getNumPennies()+" pennies");
        io.readString("\nMake sure you take it!\nThen press enter to continue.");
    }

    public void displayPoorInput(String poorInput) {
        io.print("You entered: "+poorInput);
        io.readString("Please press enter to continue.");
    }

    public void displayBalance(String userMoney) {
        io.print("Balance: $"+userMoney);
    }
}
