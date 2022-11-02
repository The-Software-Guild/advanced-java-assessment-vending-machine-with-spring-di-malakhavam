
package mmala.vendingmachine.dao;

import mmala.vendingmachine.dto.Item;
import java.util.List;
import java.math.BigDecimal;
import java.util.Map;


/**
 *
 * @author Salajrawi
 */
public interface VendingMachineDao {
    
    void removeOneItemFromInventory(String name) throws VendingMachineException;
 
    List<Item> getAllItems() throws VendingMachineException ;
        
    int getItemInventory(String name) throws VendingMachineException;

    Item getItem(String name)throws VendingMachineException;

    Map<String,BigDecimal> getMapOfItemNamesInStockWithCosts()throws VendingMachineException;
    

    
}