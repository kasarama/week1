/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author magda
 */
public class EntityTested {
    public static void main(String[] args) {
        
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
            em.persist(new Customer("Mag","Waw"));
            em.persist(new Customer("Ola","Man"));
            em.persist(new Customer("Amelia","Skov"));
            em.persist(new Customer("Jens","Jensen"));
            em.persist(new Customer("Tom","King"));
            em.persist(new Customer("Megan","Fox"));
            em.persist(new Customer("Fox","Moulder"));
        
        em.getTransaction().commit();

     

        // Close the database connection:
        em.close();
        emf.close();

        
        
    }
}
