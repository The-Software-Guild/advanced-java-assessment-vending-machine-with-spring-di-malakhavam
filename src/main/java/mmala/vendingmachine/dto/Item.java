
package mmala.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author salajrawi
 */
public class Item {
    private String name;
    private BigDecimal cost;
    private int inventory;

    
    public Item(String name, String cost, int inventory) {
       //implement
        this.name = name;
        this.cost = new BigDecimal(cost);
        this.inventory = inventory;
    }
    
    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public BigDecimal getCost() {
        return cost;
    }
    
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
     
     public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

   
    @Override
    public int hashCode() {
    //implement
    int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.cost);
        hash = 97 * hash + this.inventory;
        return hash;
    }
    

    @Override
    public boolean equals(Object obj) {
        //implement
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.inventory != other.inventory) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }
    
     @Override
    public String toString() {
        return "Item{" + "name=" + name + ", cost=" + cost + ", inventory=" + inventory + '}';
    }
     
    
}
