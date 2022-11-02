/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmala.vendingmachine.dao;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/**
 *
 * @author 18437
 */
public class VendingMachineAuditDaoImplTest {
    String testAuditFile = "testAuditFile.txt";
    AuditDao testAuditDao = new AuditDaoImpl(testAuditFile);
    
    
    public VendingMachineAuditDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testWriteAuditEntry() throws VendingMachineException {
        //ARRANGE
        String entry = "One Snickers removed.";
        
        //ACT
        testAuditDao.writeAuditEntry(entry);
        
    }
}
