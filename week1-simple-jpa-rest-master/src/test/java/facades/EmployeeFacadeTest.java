package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeFacadeTest {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade instance = EmployeeFacade.getEmployeeFacade(emf);
    private int lowestID;

    private static Employee em1;
    private static Employee em2;
    private static Employee em3;
    private static Employee em4;
    private static Employee em5;
    private static Employee em6;

    public EmployeeFacadeTest() {
    }

    @BeforeEach
    public void setUp() {
        em1 = new Employee("Magdalena Wawrzak", "Kan ike huske 8", 895268);
        em2 = new Employee("Second One", "Somewhere", 1256243);
        em3 = new Employee("Thrid One", "Not here", 789654);
        em4 = new Employee("Fourth One", "Elswhere", 123456);
        em5 = new Employee("Fifth One", "Nowhere", 654123);
        em6 = new Employee("Sixth One", "Here", 30000);
        EntityManager em = emf.createEntityManager();
        try {

            Query query = em.createQuery("DELETE from Employee e", Employee.class);

            em.getTransaction().begin();
            query.executeUpdate();

            em.persist(em1);
            em.getTransaction().commit();
            em.getTransaction().begin();

            em.persist(em2);
            em.getTransaction().commit();
            em.getTransaction().begin();

            em.persist(em3);
            em.getTransaction().commit();
            em.getTransaction().begin();

            em.persist(em4);
            em.getTransaction().commit();
            em.getTransaction().begin();

            em.persist(em5);
            em.getTransaction().commit();
            em.getTransaction().begin();

            em.persist(em6);
            em.getTransaction().commit();
           
            Query qID = em.createQuery("select MIN(e.id) from Employee e", Employee.class);
            lowestID = (int) qID.getSingleResult();

        } finally {
            em.close();
        }
    }

    @Test
    public void testCreateEmployee() {
        EntityManager em = emf.createEntityManager();
        instance.createEmployee("Seventh", "NetBeans", 10);
        String expected = "Seventh";
        Employee result = instance.findByID(6 + lowestID);
        assertTrue(expected.equals(result.getName()));

    }

    @Test 
    public void testFindByID() {
        EntityManager em = emf.createEntityManager();
        Employee result = instance.findByID(2 + lowestID);
        String expected = "Not here";
        assertTrue(result.getAddress().equals(expected));

    }

    @Test
    public void testFindByName() {
        EntityManager em = emf.createEntityManager();

        Employee result = instance.findByName("Sixth One");
        String expected = "Here";
        assertTrue(result.getAddress().equals(expected));
    }

    @Test
    public void testGetAllEmpl() {
        EntityManager em = emf.createEntityManager();
        List<Employee> result = instance.getAllEmpl();
        int expected = 6;
        assertEquals(expected, result.size());

    }

    @Test
    public void testGetEmplByName() {
        EntityManager em = emf.createEntityManager();
        List<Employee> result = instance.getEmplByName("Magdalena Wawrzak");
        String exp = "Kan ike huske 8";
        assertEquals(exp, result.get(0).getAddress());
    }

    @Test
    public void testGetEmployeesWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        Employee result = instance.getEmployeesWithHighestSalary();
        double salary = 1256243;
        assertEquals(salary, result.getSalary());

    }

}
