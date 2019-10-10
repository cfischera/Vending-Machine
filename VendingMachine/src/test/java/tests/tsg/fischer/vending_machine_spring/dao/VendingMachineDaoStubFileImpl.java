package tests.tsg.fischer.vending_machine_spring.dao;

import com.tsg.fischer.vending_machine_spring.dao.VendingMachineDao;
import com.tsg.fischer.vending_machine_spring.dao.VendingPersistenceException;
import com.tsg.fischer.vending_machine_spring.dto.Snack;
import com.tsg.fischer.vending_machine_spring.dto.Vendible;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachineDaoStubFileImpl implements VendingMachineDao {

    private Map<String, Vendible> items = new TreeMap();

    Vendible theItem = new Snack("A1", "Crispies", "2.00", "5");


    @Override
    public void loadAllItems() throws VendingPersistenceException {
        items.put(theItem.getSlotID(), theItem);
    }

    @Override
    public void saveAllChanges() throws VendingPersistenceException {

    }

    @Override
    public Vendible addItem(Vendible anItem) {
        items.put(anItem.getSlotID(), anItem);
        return anItem;
    }

    @Override
    public List<Vendible> getAllItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Vendible getAnItem(String slotId) {
        return items.get(slotId);
    }

    @Override
    public void updateAnItem(String slotId, Vendible changedItem) {
        items.put(slotId, changedItem);
    }

    @Override
    public Vendible removeAnItem(String slotId) {
        return items.remove(slotId);
    }
}
