package com.tsg.fischer.vending_machine_spring;

import com.tsg.fischer.vending_machine_spring.controller.VendingMachineController;
//import com.tsg.fischer.vending_machine_spring.dao.VendingMachineAuditDao;
//import com.tsg.fischer.vending_machine_spring.dao.VendingMachineAuditDaoFileImpl;
//import com.tsg.fischer.vending_machine_spring.dao.VendingMachineDao;
//import com.tsg.fischer.vending_machine_spring.dao.VendingMachineDaoFileImpl;
//import com.tsg.fischer.vending_machine_spring.service.VendingMachineService;
//import com.tsg.fischer.vending_machine_spring.service.VendingMachineServiceImpl;
//import com.tsg.fischer.vending_machine_spring.ui.UserIO;
//import com.tsg.fischer.vending_machine_spring.ui.UserIOConsoleImpl;
//import com.tsg.fischer.vending_machine_spring.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
//        UserIO io = new UserIOConsoleImpl();
//        VendingMachineView view = new VendingMachineView(io);
//        VendingMachineDao dao = new VendingMachineDaoFileImpl();
//        VendingMachineService serviceLayer = new VendingMachineServiceImpl(dao);
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineController controller = new VendingMachineController(dao, serviceLayer, view, auditDao);
//        controller.run();

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller =
                ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}