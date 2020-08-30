package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();
            
            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            
            

            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries

            Query query1 = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 100000", Employee.class);
            List<Employee> result1 = query1.getResultList();
            System.out.println("");
            System.out.println("");
            System.out.println("______________   1   _______________");
            for (Employee employee : result1) {
                System.out.print(employee.getSalary()+", ");
            }
            System.out.println("");
            System.out.println("");
            
            System.out.println(query1);
            
             System.out.println("");
            System.out.println("");
            System.out.println("______________   2   _______________");
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname


            Query query2 = em.createQuery("SELECT e FROM Employee e WHERE e.id ='klo999'", Employee.class);
                    Employee result2=(Employee)(query2.getSingleResult());                            
            System.out.println(result2.getFirstName()+" "+result2.getLastName());
           
            
            System.out.println("");
            System.out.println("");
            System.out.println("______________   3   _______________");
            //3) Create a query to fetch the highest salary and print the value
            
            Query query3 = em.createQuery("SELECT MAX (e.salary) FROM Employee e", Employee.class);
            System.out.println("Max selery: "+query3.getSingleResult());
            
                      

            System.out.println("");
            System.out.println("");
            System.out.println("______________   4   _______________");
            //4) Create a query to fetch the firstName of all Employees and print the names
            Query query4=em.createQuery("SELECT e.firstName FROM Employee e",Employee.class);
            List<String> list4=query4.getResultList();
            for ( String name : list4) {
                System.out.println(name);
            }
            
            System.out.println("");
            System.out.println("");
            System.out.println("______________   5   _______________");
            //5 Create a query to calculate the number of employees and print the number
            
            Query query5 = em.createQuery("SELECT COUNT(e) FROM Employee e", Employee.class);
            System.out.println("quantity of employee: "+query5.getSingleResult());
            
            
            
            System.out.println("");
            System.out.println("");
            System.out.println("______________   6   _______________");
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            Query query6 = em.createQuery("SELECT e from Employee e WHERE e.salary = ( SELECT MAX (e.salary) from Employee e)", Employee.class);
            System.out.println(query6.getSingleResult().toString());
            
        } finally {
            em.close();
            emf.close();
        }
    }

}
