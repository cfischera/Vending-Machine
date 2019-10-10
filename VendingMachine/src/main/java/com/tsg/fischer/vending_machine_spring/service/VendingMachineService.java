package com.tsg.fischer.vending_machine_spring.service;

import com.tsg.fischer.vending_machine_spring.dao.VendingPersistenceException;
import com.tsg.fischer.vending_machine_spring.dto.ChangePurse;
import com.tsg.fischer.vending_machine_spring.dto.Vendible;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService {
    public void loadMachine()
            throws VendingPersistenceException;

    public List<Vendible> getAllItemsInMachine();
    public Vendible getOneItem(String vendingSlot);

    public ChangePurse purchaseItem(String vendingSlot, BigDecimal money)
            throws VendingInsufficientFundsException,
            VendingNoItemInventoryException,
            VendingPersistenceException;
}