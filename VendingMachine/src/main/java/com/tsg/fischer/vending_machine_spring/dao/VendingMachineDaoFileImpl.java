package com.tsg.fischer.vending_machine_spring.dao;

import com.tsg.fischer.vending_machine_spring.dto.Beverage;
import com.tsg.fischer.vending_machine_spring.dto.Snack;
import com.tsg.fischer.vending_machine_spring.dto.Vendible;

import java.io.*;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao {
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Vendible> items = new TreeMap<>();

    @Override
    public void loadAllItems() throws VendingPersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch(FileNotFoundException e) {
            throw new VendingPersistenceException("Could not load inventory data into memory.", e);
        }

        String currentLine;

        Vendible currentItem;

        while(sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentItem = unmarshalItem(currentLine);
            items.put(currentItem.getSlotID(), currentItem);
        }
        sc.close();
    }

    @Override
    public void saveAllChanges() throws VendingPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingPersistenceException("Could not save item data.", e);
        }

        String itemAsText;
        List<Vendible> itemList = this.getAllItems();
        for(Vendible currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public Vendible addItem(Vendible anItem) {
        return items.put(anItem.getSlotID(), anItem);
    }

    @Override
    public List<Vendible> getAllItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Vendible getAnItem(String slotID) {
        return items.get(slotID);
    }

    @Override
    public void updateAnItem(String slotID, Vendible changedItem) {
        items.remove(slotID);
        items.put(slotID, changedItem);
    }

    @Override
    public Vendible removeAnItem(String slotID) {
        return items.remove(slotID);
    }

    public Vendible unmarshalItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);

        Vendible itemFromFile;

        if(itemTokens[0].equals("S")) {
            itemFromFile = new Snack(itemTokens[1], itemTokens[2], itemTokens[3], itemTokens[4]);
        } else if(itemTokens[0].equals("B")) {
            itemFromFile = new Beverage(itemTokens[1], itemTokens[2], itemTokens[3], itemTokens[4]);
        } else {
            itemFromFile = null;
        }

        return itemFromFile;
    }

    public String marshallItem(Vendible item) {
        String itemAsText = "";

        if(item instanceof Snack) {
            itemAsText += "S"+DELIMITER;
        } else if(item instanceof Beverage) {
            itemAsText += "B"+DELIMITER;
        }

        itemAsText += item.getSlotID()+DELIMITER;
        itemAsText += item.getItemName()+DELIMITER;
        itemAsText += item.getItemPrice()+DELIMITER;
        itemAsText += item.getQuantity();
        return itemAsText;
    }
}
