
package mmala.vendingmachine.service;

import mmala.vendingmachine.dao.AuditDao;
import mmala.vendingmachine.dao.VendingMachineDao;
import mmala.vendingmachine.dao.VendingMachineException;
import mmala.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;
import mmala.vendingmachine.dto.Change;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author salajrawi
 */
@Component
public class VendingMachineServiceImpl implements VendingMachineService{

    private AuditDao auditDao;
    private VendingMachineDao dao;

      @Autowired  
    public VendingMachineServiceImpl(AuditDao auditDao, VendingMachineDao dao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }

     
     @Override
    public void checkIfEnoughMoney(Item item, BigDecimal inputMoney) throws VendingMachineInsufficientFundsException {
        //Checks if the user has input enough money to buy selected item
        //If the cost of the item is greater than the amount of money put in
        if (item.getCost().compareTo(inputMoney)==1) {
            throw new VendingMachineInsufficientFundsException (
            "ERROR: insufficient funds, your balance $"+ inputMoney);  
        }
    }
    
    @Override
    public Map<String, BigDecimal>  getItemsInStockWithCosts () throws VendingMachineException{
        //Map of key=name, value=cost
         Map<String, BigDecimal> itemsInStockWithCosts = dao.getMapOfItemNamesInStockWithCosts();
         return itemsInStockWithCosts;
    }
    
    @Override
    public Map<BigDecimal, BigDecimal> getChangePerCoin(Item item, BigDecimal money) {
        BigDecimal itemCost = item.getCost();
        Map<BigDecimal, BigDecimal> changeDuePerCoin = Change.changeDuePerCoin(itemCost, money);
        return changeDuePerCoin;
    }
    
    @Override
    public Item getItem(String name, BigDecimal inputMoney) throws VendingMachineInsufficientFundsException, VendingMachineItemInventoryException, VendingMachineException, VendingMachineItemInventoryException{
        //implement
        Item wantedItem = dao.getItem(name);   //the inputs are case sensitive.
        
        //If the wanted item returns null, the item does not exist in the items map
        if (wantedItem == null) {
            throw new VendingMachineItemInventoryException (
                "ERROR: there are no " + name + "'s in the vending machine.");
        }
        
        // Check if the user has input enough money
        checkIfEnoughMoney(wantedItem,inputMoney);
        
        // If they have, check that the item is in stock and if so, remove one item from the inventory
        removeOneItemFromInventory(name);
        return wantedItem;
    }
    
    @Override
    public void removeOneItemFromInventory (String name) throws VendingMachineItemInventoryException, VendingMachineException {
        //Remove one item from the inventory only when there are items to be removed, i.e. inventory>0.
        if (dao.getItemInventory(name)>0) {
            dao.removeOneItemFromInventory(name);
            //if an items removed, write to the audit log
            auditDao.writeAuditEntry(" One " + name + " removed");
        } else {
            //If there are no items left to remove, throw an exception
            throw new VendingMachineItemInventoryException (
            "ERROR: " + name + " is out of stock.");
        }
    }

    
}
