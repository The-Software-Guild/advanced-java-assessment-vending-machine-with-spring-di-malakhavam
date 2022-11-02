
package mmala.vendingmachine.dto;

import mmala.vendingmachine.service.VendingMachineInsufficientFundsException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author salajrawi
 */
public class Change {
    
    public static BigDecimal changeDueInPennies (BigDecimal itemCost, BigDecimal money) {
        BigDecimal changeDueInPennies = (money.subtract(itemCost)).multiply(new BigDecimal("100"));
        System.out.println("Change due: $" + (changeDueInPennies.divide(new BigDecimal("100"),2,RoundingMode.HALF_UP).toString()));
        return changeDueInPennies;
    }
    
    public static Map<BigDecimal, BigDecimal> changeDuePerCoin (BigDecimal itemCost, BigDecimal money) {
        Coin[] coinEnumArray = Coin.values();
        ArrayList <String> coinStringList = new ArrayList<>();
        for (Coin coin : coinEnumArray) {
            coinStringList.add(coin.toString());
        }

          ArrayList<BigDecimal> coins = new ArrayList<BigDecimal>(); 
          for (String coin:coinStringList) {
              coins.add(new BigDecimal(coin));
          }
          
        BigDecimal changeDueInPennies = changeDueInPennies(itemCost, money);
        //Calculates the number of quarters, dimes, nickels and pennies due 
        //back to the user.
        BigDecimal noOfCoin;
        BigDecimal zero = new BigDecimal("0");
        //Map <coin, noOfCoin>
        Map <BigDecimal, BigDecimal> amountPerCoin = new HashMap<>();
        
        //for every coin in the array:
        for (BigDecimal coin : coins) {
            //if the change is greater than or equal to the coin amount
            if (changeDueInPennies.compareTo(coin) >= 0) {
                //If the coin amounts does not exactly divide by the change amount
                if (!changeDueInPennies.remainder(coin).equals(zero)) {
                    //the number of coins of coin[i] required will be the floor division of change amount/coin
                    noOfCoin = changeDueInPennies.divide(coin,0,RoundingMode.DOWN);
                    //add the type of coin and amount of coin to the map
                    amountPerCoin.put(coin, noOfCoin);
                    //the change amount is updated to be the remaining amount
                    changeDueInPennies = changeDueInPennies.remainder(coin);
                    //if the change amount is less than or equal to 0, stop the loop
                    if (changeDueInPennies.compareTo(zero)<0) {  
                        break;
                    }
                    //if the change divided by the coin is an exact number/integer
                } else if (changeDueInPennies.remainder(coin).equals(zero)) {  //could change to just else
                    noOfCoin = changeDueInPennies.divide(coin,0,RoundingMode.DOWN);
                    amountPerCoin.put(coin, noOfCoin);
                    //if the change amount if less than or equal to 0, stop the loop
                    if ((changeDueInPennies.compareTo(zero))<0) {
                        break;
                    }
                }
            } else {
               
            }
        }//end of for loop
        return amountPerCoin;
    }

    
    
    
    
}