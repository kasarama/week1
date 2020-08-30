package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Employee;
import facades.EmployeeFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeDemo {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmpl() {
        return GSON.toJson(facade.getAllEmpl());
    }
    
    
    @Path("highestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighest() {
        return GSON.toJson(facade.getEmployeesWithHighestSalary());
    }
 
    
    @Path("emplbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByID(@PathParam("id") int id) {
        return GSON.toJson(facade.findByID(id));
    }
    
     
    @Path("emplbyname/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByID(@PathParam("name") String name) {
        return GSON.toJson(facade.findByName(name));
    }
    
     

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
