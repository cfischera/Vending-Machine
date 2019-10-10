package tests.tsg.fischer.vending_machine_spring.service;

import com.tsg.fischer.vending_machine_spring.dao.VendingMachineDao;
import com.tsg.fischer.vending_machine_spring.dto.ChangePurse;
import com.tsg.fischer.vending_machine_spring.dto.Vendible;
import com.tsg.fischer.vending_machine_spring.service.VendingMachineService;
import com.tsg.fischer.vending_machine_spring.service.VendingMachineServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.tsg.fischer.vending_machine_spring.dao.VendingMachineDaoStubFileImpl;

import java.math.BigDecimal;

public class VendingMachineServiceTest {

    private VendingMachineService service;

    public VendingMachineServiceTest() {
        VendingMachineDao dao = new VendingMachineDaoStubFileImpl();

        this.service = new VendingMachineServiceImpl(dao);

    }

    @Test
    public void testLoad() {
        try {
            service.loadMachine();
        } catch(Exception e) {
            System.out.println("test fail");
        }
    }

    @Test
    public void testGetAllItems() {
        try {
            service.loadMachine();
            service.getAllItemsInMachine();
        } catch(Exception e) {
            System.out.println("test fail");
        }
    }

    @Test
    public void testGetOneItem() {
        Vendible item = null;
        try {
            service.loadMachine();
            item = service.getOneItem("A1");
        } catch(Exception e) {
            System.out.println("test fail");
        }

        Assertions.assertEquals(item.getSlotID(), "A1", "slot Id's should match");
    }

    @Test
    public void testPurchaseAnItem() {
        ChangePurse change = null;
        try {
            service.loadMachine();
            change = service.purchaseItem("A1", new BigDecimal("4"));
        } catch(Exception e) {
            System.out.println("test fail");
        }

        Assertions.assertEquals(change.getNumQuarters(), 8, "change should be 8 quarters");
    }
}
