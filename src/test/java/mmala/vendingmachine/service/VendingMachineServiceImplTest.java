
package mmala.vendingmachine.service;

import mmala.vendingmachine.service.VendingMachineServiceImpl;
import mmala.vendingmachine.service.VendingMachineItemInventoryException;
import mmala.vendingmachine.service.VendingMachineService;
import mmala.vendingmachine.service.VendingMachineInsufficientFundsException;
import mmala.vendingmachine.dao.AuditDao;
import mmala.vendingmachine.dao.AuditDaoImpl;
import mmala.vendingmachine.dao.VendingMachineDao;
import mmala.vendingmachine.dao.VendingMachineDaoImpl;
import mmala.vendingmachine.dao.VendingMachineException;
import mmala.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 *
 * @author salajrawi
 */
public class VendingMachineServiceImplTest {
    private VendingMachineService service;
    
    VendingMachineDao testDao = new VendingMachineDaoImpl("VendingMachineTestFile.txt");  
    String testAuditFile = "testAuditFile.txt";
    AuditDao testAuditDao = new AuditDaoImpl(testAuditFile);
    VendingMachineService testService = new VendingMachineServiceImpl(testAuditDao, testDao);
    
    
    public VendingMachineServiceImplTest() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.service = appContext.getBean("serviceLayer", VendingMachineServiceImpl.class);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        
    }
    @Test
    public void testCheckIfEnoughMoney() {
        //ARRANGE
        Item cokeClone = new Item("Coke");
        cokeClone.setCost(new BigDecimal("1.60"));
        cokeClone.setInventory(9);
        
        BigDecimal enoughMoney = new BigDecimal("2.00");
        BigDecimal notEnoughMoney = new BigDecimal("1.59");
        
        //ACT - enough money
        try {
            testService.checkIfEnoughMoney(cokeClone, enoughMoney);
        } catch (VendingMachineInsufficientFundsException e){
            fail("There is sufficient funds, exception should not have been thrown");
        }
        //ACT - not enough money
        try {
            testService.checkIfEnoughMoney(cokeClone, notEnoughMoney);
            fail("There insufficient funds, exception should have been thrown");
        } catch (VendingMachineInsufficientFundsException e){
        }
    }
    
    @Test
    public void testGetItemsInStockWithCosts() {
        
    }
    
    @Test
    public void testGetChangePerCoin(){
        //ARRANGE
        Item cokeClone = new Item("Coke");
        cokeClone.setCost(new BigDecimal("1.60"));
        cokeClone.setInventory(9);
        
        BigDecimal money = new BigDecimal("2.50");
        
        //Change should be $0.90: 25c: 3, 10c: 1, 5c:1
        Map<BigDecimal, BigDecimal> expectedChangePerCoin = new HashMap<>();
        expectedChangePerCoin.put(new BigDecimal("0.25"), new BigDecimal("3"));
        expectedChangePerCoin.put(new BigDecimal("0.10"), new BigDecimal("1"));
        expectedChangePerCoin.put(new BigDecimal("0.05"), new BigDecimal("1"));
        
        //ACT
        Map<BigDecimal, BigDecimal> changePerCoin = testService.getChangePerCoin(cokeClone, money);
        
        //ASSERT
        assertEquals(changePerCoin.size(), 3, "There should be three coins");
        
        //assertTrue(changePerCoin.entrySet().equals(expectedChangePerCoin.entrySet()), "The change per coin map should be equal to the expected.");
        //need to find out how to compare these as this doesnt work
    }
    
    @Test
    public void testGetItem() throws VendingMachineInsufficientFundsException, VendingMachineException, VendingMachineItemInventoryException {
        //ARRANGE
        Item snickersClone = new Item("Snickers");
        snickersClone.setCost(new BigDecimal("2.10"));
        snickersClone.setInventory(0);
        BigDecimal money = new BigDecimal("3.00");
        
        Item oreoClone = new Item("Oreo");
        oreoClone.setCost(new BigDecimal("2.10"));
        oreoClone.setInventory(testDao.getItemInventory("Oreo"));
        
        Item itemWanted = null;
        //ACT
        try {
            itemWanted = testService.getItem("Snickers", money);
            fail("The item wanted is out of stock.");
        }catch (VendingMachineItemInventoryException e) {
        }
        try {
            itemWanted = testService.getItem("Oreo", money);
        }catch (VendingMachineItemInventoryException e) {
            if (testDao.getItemInventory("Oreo")>0){
            fail("The item wanted is in stock.");
        } 

        //ASSERT
        assertNotNull(itemWanted, "change should not be null");
        assertEquals(itemWanted, oreoClone,"The item retrieved should be snickers");
    }
    }
    
    @Test
    public void testRemoveOneItemFromInventory() throws VendingMachineException {
        //ARRANGE
        String name = "Snickers";
        
        //There are no snickers left
        try{
            //ACT
            testService.removeOneItemFromInventory(name);
            //ASSERT
            fail("There are no snickers left, exception should be thrown");
        } catch (VendingMachineItemInventoryException e) {  
        }
        
        String oreo = "Oreo";
        try{
            //ACT
            testService.removeOneItemFromInventory(oreo);
        } catch (VendingMachineItemInventoryException e) {
            if (testDao.getItemInventory(oreo)>0){
                fail("Oreo are in stock, exception should not be thrown");
            }
        } 
    }
    
}
