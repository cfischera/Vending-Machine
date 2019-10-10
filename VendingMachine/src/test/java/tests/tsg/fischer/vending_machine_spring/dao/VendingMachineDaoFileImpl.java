package tests.tsg.fischer.vending_machine_spring.dao;

import com.tsg.fischer.vending_machine_spring.dao.VendingMachineDaoFileImpl;
import com.tsg.fischer.vending_machine_spring.dto.Snack;
import com.tsg.fischer.vending_machine_spring.dto.Vendible;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class VendingMachineDaoFileImplTest {
    VendingMachineDaoFileImpl testDao;

    public VendingMachineDaoFileImplTest() {
        testDao = new VendingMachineDaoFileImpl();
    }

    @Test
    public void unmarshalItemTest() {

        String snackLine = "S::T1::A Good Snack::3.75::5";

        Vendible fromLine = testDao.unmarshalItem(snackLine);

        Assertions.assertEquals(true, fromLine instanceof Snack, "object type should be Snack");
        Assertions.assertEquals("T1", fromLine.getSlotID(), "slotID should be T1");
        Assertions.assertEquals("A Good Snack", fromLine.getItemName(), "item ame should be A Good Snack");
        Assertions.assertEquals(new BigDecimal("3.75"), fromLine.getItemPrice(), "item price should be 3.75");
        Assertions.assertEquals(5, fromLine.getQuantity(), "item quantity should be 5");
    }

    @Test
    public void marshallItemTest() {
        Vendible toMarshall = new Snack("T2", "A Bad Snack", "2.25", "20");

        String snackLine = testDao.marshallItem(toMarshall);

        Assertions.assertEquals("S::T2::A Bad Snack::2.25::20", snackLine, "Lines should match");
    }

    @Test
    public void circleOfMarshallingTest(){

        Vendible toMarshall = new Snack("T3", "An Adequate Snack", "1.50", "10");

        String tempSnackText = testDao.marshallItem(toMarshall);
        Vendible shouldLookLikeOriginal = testDao.unmarshalItem(tempSnackText);

        Assertions.assertEquals(toMarshall, shouldLookLikeOriginal, "Llamas should match");
    }
}