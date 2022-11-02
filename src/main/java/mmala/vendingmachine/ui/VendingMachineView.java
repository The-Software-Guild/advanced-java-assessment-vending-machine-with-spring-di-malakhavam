
package mmala.vendingmachine.ui;

import mmala.vendingmachine.dto.Coin;
import mmala.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;



/**
 *
 * @author salajrawi
 */
@Component
public class VendingMachineView {
    
    private UserIO io;
    
    @Autowired
    public VendingMachineView (UserIO io){
        this.io = io;
    }
     
    public BigDecimal getMoney() {
        return io.readBigDecimal("Please input the amount money in dollars before making selection");
    }
    
    public void displayMenuBanner() {
        io.print("<<< Menu >>>");
    }
    
    public void displayMenu(Map<String, BigDecimal> itemsInStockWithCosts){
        itemsInStockWithCosts.entrySet().forEach(entry ->{
        System.out.println(entry.getKey() + ": $" +entry.getValue());
        });
    }
    
     public String getItemSelection(){
        return io.readString("Please select an item from the menu or select 'exit' to quit");
    }
    
    
    public void displayEnjoyBanner(String name) {
        io.print("Here is your change.");
        io.print("Enjoy your " + name + "!");
    }
    
    public void displayInsufficientFundsMsg(BigDecimal money){
        io.print("Insufficent funds, your balance is  $" + money);
    }
    
    public void displayItemOutOfStockMsg(String name){
        io.print("Error, " + name + " is out of stock.");
    }   

    public void displayChangeDuePerCoin(Map<BigDecimal, BigDecimal> changeDuePerCoin) {
        changeDuePerCoin.entrySet().forEach(entry ->{
                 System.out.println(entry.getKey() + "c : " +entry.getValue());
         });
    }

    public void displayExitBanner() {
        io.print("Good Bye!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public void displayErrorMessage (String errorMsg) {
        io.print("<<< Error >>>");
        io.print(errorMsg);
    }
    
    public void displayPleaseTryAgainMsg() {
        io.print("Please select another item.");
    }


}
