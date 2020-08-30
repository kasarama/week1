/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author magda
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

       
    
    
    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("pu");

        instance = getCustomerFacade(emf);

         instance.addCustomer("Eryk", "Pryz");
        System.out.println("Find by ID: " + ((Customer) instance.findByID(5)).toString());

        List<Customer> allC = instance.getAllCustomers();

        for (Customer customer : allC) {
            System.out.println(customer.toString());

        }
        List<Customer> allLastName = instance.findByLastName("Waw");
        for (Customer customer : allLastName) {
            System.out.println(customer.getId()+". "+customer.getFirstName());
        }

        System.out.println("qantity: "+instance.getNumberOfCustomers());
    }
    

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer addCustomer(String firstName, String lastName) {
        Customer Customer = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(Customer);
            em.getTransaction().commit();
            return Customer;
        } finally {
            em.close();
        }
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer Customer = em.find(Customer.class, id);
            return Customer;
        } finally {
            em.close();
        }
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select c from Customer c where c.lastName = :lastName", Customer.class);
            query.setParameter("lastName", lastName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Long getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("Select count(c) from Customer c ", Customer.class);
            return (Long)query.getSingleResult();
        } finally {
            em.close();
        }
    }

}
