package com.tsg.fischer.vending_machine_spring.controller;

import com.tsg.fischer.vending_machine_spring.dao.VendingMachineAuditDao;
import com.tsg.fischer.vending_machine_spring.dao.VendingMachineDao;
import com.tsg.fischer.vending_machine_spring.dao.VendingPersistenceException;
import com.tsg.fischer.vending_machine_spring.dto.ChangePurse;
import com.tsg.fischer.vending_machine_spring.dto.Vendible;
import com.tsg.fischer.vending_machine_spring.service.VendingInsufficientFundsException;
import com.tsg.fischer.vending_machine_spring.service.VendingMachineService;
import com.tsg.fischer.vending_machine_spring.service.VendingNoItemInventoryException;
import com.tsg.fischer.vending_machine_spring.ui.VendingMachineView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class VendingMachineController {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    private VendingMachineService serviceLayer;
    private VendingMachineView view;

    public VendingMachineController(VendingMachineDao dao, VendingMachineService serviceLayer, VendingMachineView view,
                                    VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.serviceLayer = serviceLayer;
        this.view = view;
        this.auditDao = auditDao;
    }

    public void run() {
        try {
            auditStart();
        } catch(VendingPersistenceException e) {
            displayError(e.getMessage());
        }
        boolean keepVending = true, transactionOccurring;
        while(keepVending) {
            transactionOccurring = true;
            BigDecimal userMoney = new BigDecimal("0");
            try {
                loadMachine();
            } catch (VendingPersistenceException e) {
                displayError(e.getMessage());
            }

            showInventory();
            while(transactionOccurring) {
                displayBalance(userMoney);
                int userAction = getAction();
                if (userAction == 1) {
                    displayBalance(userMoney);
                    try {
                        userMoney = getUserMoney(userMoney);
                    } catch(VendingPersistenceException e) {
                        displayError(e.getMessage());
                    }
                } else if (userAction == 2) {
                    showInventory();
                    String userChoice = getChoice();
                    try {
                        ChangePurse change = getChange(userChoice, userMoney);
                        dispenseChange(change);
                        transactionOccurring = false;
                    } catch (VendingInsufficientFundsException e) {
                        displayError(e.getMessage());
                    } catch (VendingPersistenceException | VendingNoItemInventoryException e) {
                        displayError(e.getMessage());
                    }
                } else if (userAction == 3) {
                    if(userMoney.doubleValue()>0.0) {
                        try {
                            dispenseChange(getChange("", userMoney));
                        } catch(Exception e) {
                            displayError(e.getMessage());
                        }
                    }
                    transactionOccurring = false;
                    keepVending = false;
                }
            }
        }
        try {
            auditEnd();
        } catch(VendingPersistenceException e) {
            displayError(e.getMessage());
        }
        endSession();
    }

    private void showInventory() {
        List<Vendible> items = serviceLayer.getAllItemsInMachine();
        view.displayInventoryBanner();
        view.displayAllItems(items);
    }

    private int getAction() {
        return view.getAction();
    }

    private void endSession() {
        view.displayFarewell();
        System.exit(0);
    }

    private BigDecimal getUserMoney(BigDecimal userMoney) throws VendingPersistenceException {
        auditDao.writeAuditEntry("Received user money.");
        return userMoney.add(new BigDecimal(view.getUserMoney())).setScale(2, RoundingMode.HALF_UP);
    }

    private String getChoice() {
        return view.getChoice();
    }

    private void displayError(String msg) {
        try {
            auditDao.writeAuditEntry("Error: " + msg);
        } catch (VendingPersistenceException e) {
            System.out.println("I don't know how to fix this yet.");
            System.exit(0);
        }
        view.displayErrorMessage(msg);
    }

    private void loadMachine() throws VendingPersistenceException {
        serviceLayer.loadMachine();
        auditDao.writeAuditEntry("Machine loaded.");
    }

    private ChangePurse getChange(String userChoice, BigDecimal userMoney) throws VendingInsufficientFundsException,
            VendingNoItemInventoryException, VendingPersistenceException {
        auditDao.writeAuditEntry("Change calculated");
        return serviceLayer.purchaseItem(userChoice, userMoney);
    }

    private void dispenseChange(ChangePurse change) throws VendingPersistenceException {
        auditDao.writeAuditEntry("Change dispensed");
        view.dispenseChange(change);
    }

    private void displayPoorUserInput(BigDecimal poorInput) {
        view.displayPoorInput(poorInput.toString());
    }

    private void displayBalance(BigDecimal userMoney) {
        view.displayBalance(userMoney.toString());
    }

    private void auditStart() throws VendingPersistenceException {
        auditDao.writeAuditEntry("Machine start.");
    }

    private void auditEnd() throws VendingPersistenceException {
        auditDao.writeAuditEntry("Machine end.");
    }
}
