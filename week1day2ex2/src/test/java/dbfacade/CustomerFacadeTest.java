/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author magda
 */
public class CustomerFacadeTest {

    EntityManagerFactory emf;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @org.junit.Test
    public void testAddCustomer() {
        emf = Persistence.createEntityManagerFactory("pu");
        System.out.println("addCustomer");
        String firstName = "Test";
        String lastName = "Testowski";
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        Customer result = instance.addCustomer(firstName, lastName);
        Customer expResult = instance.findByLastName("Testowski").get(0);
        assertEquals(expResult.getFirstName(), result.getFirstName());

    }

    /**
     * Test of findByID method, of class CustomerFacade.
     */
    @org.junit.Test
    public void testFindByID() {
        emf = Persistence.createEntityManagerFactory("pu");
        System.out.println("findByID");
        int id = 22;
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        Customer expResult = new Customer("Ola", "Man");
        Customer result = instance.findByID(id);
        assertTrue(expResult.getFirstName().equals(result.getFirstName()));

    }

    /**
     * Test of getAllCustomers method, of class CustomerFacade.
     */
    @org.junit.Test
    public void testGetAllCustomers() {
        System.out.println("getAllCustomers");

        emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        Customer expResult = new Customer("Megan","Fox");
        List<Customer> result = instance.getAllCustomers();
        assertTrue(expResult.getFirstName().equals(result.get(20).getFirstName()));
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @org.junit.Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        String lastName = "Moulder";
        String expResult = "Fox";
        List<Customer> result = instance.findByLastName(lastName);
        assertTrue(expResult.equals(result.get(0).getFirstName()));
        
        
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @org.junit.Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");
        emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        Long expResult = 24L;
        Long result = instance.getNumberOfCustomers();
        assertTrue(expResult< result);
    }

}
