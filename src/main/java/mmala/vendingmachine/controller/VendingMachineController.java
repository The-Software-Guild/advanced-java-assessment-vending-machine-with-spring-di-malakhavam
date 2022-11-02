
package mmala.vendingmachine.controller;

import mmala.vendingmachine.dao.VendingMachineException;
import mmala.vendingmachine.dto.Item;
import mmala.vendingmachine.service.VendingMachineInsufficientFundsException;
import mmala.vendingmachine.service.VendingMachineItemInventoryException;
import mmala.vendingmachine.service.VendingMachineService;
import mmala.vendingmachine.ui.UserIO;
import mmala.vendingmachine.ui.UserIOImpl;
import mmala.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author User
 */

@Component
public class VendingMachineController {
    private UserIO io = new UserIOImpl();
    private VendingMachineView view;
    private VendingMachineService service;

    @Autowired
    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        String itemSelection = "";
        BigDecimal inputMoney;
        view.displayMenuBanner();
        try {
            getMenu();
        } catch (VendingMachineException e) {
            view.displayErrorMessage(e.getMessage());
        }
        inputMoney = getMoney();
            while (keepGoing) {
            try {
                //Display the menu and get a selection
                itemSelection = getItemSelection();
                
                //If the user selects Exit, the program is exited
                if (itemSelection.equalsIgnoreCase("Exit")) {
                    keepGoing = false;
                    break;
                }
                getItem(itemSelection, inputMoney);
                keepGoing = false;
                break;

            } catch (VendingMachineInsufficientFundsException | VendingMachineItemInventoryException | VendingMachineException e) {
                view.displayErrorMessage(e.getMessage());
                view.displayPleaseTryAgainMsg();
            }
            }
            exitMessage();

    }
    private void getMenu() throws VendingMachineException {
        Map<String, BigDecimal> itemsInStockWithCosts = service.getItemsInStockWithCosts();
        view.displayMenu(itemsInStockWithCosts);
    }    
    
    private BigDecimal getMoney() {
        return view.getMoney();
    }
    
    private String getItemSelection(){
        return view.getItemSelection();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void getItem(String name, BigDecimal money) throws VendingMachineInsufficientFundsException, VendingMachineItemInventoryException, VendingMachineException {
        Item wantedItem = service.getItem(name, money);
        Map<BigDecimal, BigDecimal> changeDuePerCoin = service.getChangePerCoin(wantedItem, money);
        view.displayChangeDuePerCoin(changeDuePerCoin);
        view.displayEnjoyBanner(name);
    }
    

}
