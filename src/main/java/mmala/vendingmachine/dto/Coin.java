
package mmala.vendingmachine.dto;

/**
 *
 * @author 18437
 */

public enum Coin {
    //Enum is a special class that represents a group of constants, like final variables
    QUARTER(25), 
    DIME(10), 
    NICKEL(5), 
    PENNY(1);
    private final int value;
    
    Coin (int value) {
        this.value = value;
    }
    
   public int getValue() {
        return value;
    }
         
    @Override
    public String toString(){
        switch (this) {
            case QUARTER:
                return "25";
            case DIME:
                return "10";
            case NICKEL:
                return "5";
            case PENNY:
                return "1";
        }
        return null;
    }
    
    
}

//Enums are classes that have a fixed set of constants or varibales that do not tend to change.
//The enumeration in Java is achieved using the keyword enum. The java enum constants are static
//and final implicitly.
