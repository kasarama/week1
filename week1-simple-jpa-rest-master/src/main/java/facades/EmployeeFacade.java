
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author magda
 */
public class EmployeeFacade {
    
    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;
    

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
   
    
    public Employee createEmployee(String name, String address, double salary) {
        Employee empl = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(empl);
            em.getTransaction().commit();
            return empl;
        } finally {
            em.close();
        }        
    }
    
    public Employee findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee emp = em.find(Employee.class, id);
            return emp;
        } finally {
            em.close();
        }
    }
    
    public Employee findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("Select e from Employee e where e.name=:name", Employee.class);
            query.setParameter("name", name);
            return (Employee)query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<Employee> getAllEmpl()    {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select e from Employee e ", Employee.class);
            
            return query.getResultList();
        } finally {
            em.close();
        }
        
    }
    public List<Employee> getEmplByName(String name)    {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select e from Employee e where e.name=:name", Employee.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
        
    }
    public Employee getEmployeesWithHighestSalary() {
         EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select e from Employee e where e.salary=(select MAX(e.salary) from Employee e)", Employee.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
