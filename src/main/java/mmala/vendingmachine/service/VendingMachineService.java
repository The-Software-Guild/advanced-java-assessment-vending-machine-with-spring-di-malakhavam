
package mmala.vendingmachine.service;

import mmala.vendingmachine.dao.VendingMachineException;
import mmala.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author salajrawi
 */
public interface VendingMachineService {
   void checkIfEnoughMoney(Item item, BigDecimal inputMoney)throws 
            VendingMachineInsufficientFundsException;
    
    void removeOneItemFromInventory(String name) throws 
           VendingMachineItemInventoryException, 
            VendingMachineException;
    
    Map<String, BigDecimal>  getItemsInStockWithCosts () throws 
            VendingMachineException;

    Item getItem(String name, BigDecimal inputMoney) throws 
            VendingMachineInsufficientFundsException, 
            VendingMachineItemInventoryException, 
            VendingMachineException;
    
    Map<BigDecimal, BigDecimal> getChangePerCoin(Item item, BigDecimal money);
    
}