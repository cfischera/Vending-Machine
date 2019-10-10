package com.tsg.fischer.vending_machine_spring.dao;

import com.tsg.fischer.vending_machine_spring.dto.Vendible;

import java.util.List;

public interface VendingMachineDao {
    public void loadAllItems()
            throws VendingPersistenceException;
    public void saveAllChanges()
            throws VendingPersistenceException;
    public Vendible addItem(Vendible anItem);
    public List<Vendible> getAllItems();
    public Vendible getAnItem(String slotId);
    public void updateAnItem(String slotId, Vendible changedItem);
    public Vendible removeAnItem(String slotId);
}