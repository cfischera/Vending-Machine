package com.tsg.fischer.vending_machine_spring.dao;

public interface VendingMachineAuditDao {

    public void writeAuditEntry(String entry) throws VendingPersistenceException;

}
// Notes on an AuditDao from Jason

// minimum: have a log file logging events

// auditing in such a way that you can visually re-create the state of the application base on the log

// ex: yyyy-mm-dd hh:mm:ss : [Event]

// events: load (includes all items and quantities)
// added money (amount)
// vended (item and price)
// change dispensed (amount)
// any errors or exceptions
// file save message that mirrors the load