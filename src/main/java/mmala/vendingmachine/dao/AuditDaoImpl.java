
package mmala.vendingmachine.dao;

import mmala.vendingmachine.dao.AuditDao;
import mmala.vendingmachine.dao.VendingMachineException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import mmala.vendingmachine.dto.Item;

/**
 *
 * @author salajrawi
 */

public class AuditDaoImpl implements AuditDao {

    private final String AUDIT_FILE;
    //Default constructor
    public AuditDaoImpl() {
        this.AUDIT_FILE = "audit.txt";
    }
    //Contructor for testing
    public AuditDaoImpl(String auditTestFile) {
        this.AUDIT_FILE = auditTestFile;
    }
    

    @Override
    public void writeAuditEntry(String entry) throws VendingMachineException {
         PrintWriter out;
         try {
             out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
         } catch (IOException e) {
             throw new VendingMachineException("Could not save audit information", e);
         }
         LocalDateTime timestamp = LocalDateTime.now();
         out.println(timestamp.toString() + " : " + entry);
         out.flush();
    }

    

   
}
