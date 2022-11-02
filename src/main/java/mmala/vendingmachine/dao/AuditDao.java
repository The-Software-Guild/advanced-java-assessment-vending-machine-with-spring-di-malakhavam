
package mmala.vendingmachine.dao;

/**
 *
 * @author salajrawi
 */
public interface AuditDao {
    public void writeAuditEntry(String entry) throws VendingMachineException;

    
}
