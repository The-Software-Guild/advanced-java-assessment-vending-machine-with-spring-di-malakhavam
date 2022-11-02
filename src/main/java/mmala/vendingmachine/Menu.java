/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmala.vendingmachine;
import mmala.vendingmachine.dto.Item;
import mmala.vendingmachine.ui.UserIO;
import mmala.vendingmachine.ui.UserIOImpl;


/**
 *
 * @author 18437
 */
public class Menu {
   public static void main(String[] args) {
        UserIO io = new UserIOImpl();

        Item kitKat = new Item("kitKat", "1.50", 10);
        Item snickers = new Item("snickers", "2.10", 10);
        Item milkyWay = new Item("milkyWay", "1.30", 10);
        Item coke = new Item("coke", "1.60", 10);
        Item lays = new Item("lays", "2.50", 10);
        Item starburst = new Item("starburst", "1.20", 10);
        
        
        boolean keepGoing = true;
        int itemSelection = 0;
        while(keepGoing) {
            io.print("Vending Machine Selection: ");
            io.print("1. Kitkat, $1.50");
            io.print("2. Snickers, $2.10");
            io.print("3. MilkyWay, $1.30");
            io.print("4. Coke, $1.60");
            io.print("5. Lays, $2.50");
            io.print("6. Exit");
            
            itemSelection = io.readInt("Please select an item from the above list", 1,7);
            switch (itemSelection) {
                case 1:
                    //System.out.println("1");
                    break;
                case 2:
                    //item2();
                    System.out.println("2");
                    break;
                case 3:
                    //item3();
                    System.out.println("3");
                    break;
                case 4:
                    //item4();
                    System.out.println("4");
                    break;
                case 5:
                    //item5();
                    System.out.println("5");
                    break;    
                case 6:
                    System.out.println("exit");
                    keepGoing = false;
                    break;
                default:
                    io.print("unknown command");
            }
        }
        io.print("Good bye");
    }
      
}
