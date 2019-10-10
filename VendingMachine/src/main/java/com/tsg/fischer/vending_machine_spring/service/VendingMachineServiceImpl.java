package com.tsg.fischer.vending_machine_spring.service;

import com.tsg.fischer.vending_machine_spring.dao.VendingMachineDao;
import com.tsg.fischer.vending_machine_spring.dao.VendingPersistenceException;
import com.tsg.fischer.vending_machine_spring.dto.Beverage;
import com.tsg.fischer.vending_machine_spring.dto.ChangePurse;
import com.tsg.fischer.vending_machine_spring.dto.Snack;
import com.tsg.fischer.vending_machine_spring.dto.Vendible;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class VendingMachineServiceImpl implements VendingMachineService {
    private VendingMachineDao dao;

    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public void loadMachine() throws VendingPersistenceException {
        dao.loadAllItems();
    }

    @Override
    public List<Vendible> getAllItemsInMachine() {
        return dao.getAllItems();
    }

    @Override
    public Vendible getOneItem(String vendingSlot) {
        return dao.getAnItem(vendingSlot);
    }

    @Override
    public ChangePurse purchaseItem(String vendingSlot, BigDecimal money)
            throws VendingInsufficientFundsException, VendingNoItemInventoryException, VendingPersistenceException {
        if(vendingSlot.equals("")) {
            return new ChangePurse(calculateChange(money));
        }
        dao.loadAllItems();

        ChangePurse change;

        Vendible theItem = getOneItem(vendingSlot);

        if(theItem == null || theItem.getQuantity() == 0) {
            throw new VendingNoItemInventoryException("No such item is available.");
        }

        BigDecimal remainder = money.subtract(theItem.getItemPrice());

        if(remainder.doubleValue() < 0) {
            throw new VendingInsufficientFundsException("Insufficient funds.");
        } else {
            int[] coins = calculateChange(remainder);
            change = new ChangePurse(coins[0], coins[1], coins[2], coins[3]);
        }

        if(theItem instanceof Snack) {
            dao.updateAnItem(vendingSlot, new Snack(theItem.getSlotID(), theItem.getItemName(), theItem.getItemPrice().toString(), Integer.toString(theItem.getQuantity() - 1)));
        } else if(theItem instanceof Beverage) {
            dao.updateAnItem(vendingSlot, new Beverage(theItem.getSlotID(), theItem.getItemName(), theItem.getItemPrice().toString(), Integer.toString(theItem.getQuantity() - 1)));

        }

        dao.saveAllChanges();

        return change;
    }

    private int[] calculateChange(BigDecimal difference) {
        int[] coins = new int[4];
        double change = difference.setScale(2, RoundingMode.HALF_UP).doubleValue();
        while(change >= 0.25) {
            coins[3]++;
            change -= 0.25;
        }
        while(change >= 0.10) {
            coins[2]++;
            change -= 0.10;
        }
        while(change >= 0.05) {
            coins[1]++;
            change -= 0.05;
        }
        while(change >= 0.01) {
            coins[0]++;
            change -= 0.01;
        }
        return coins;
    }
}
