/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author magda
 */
@Path("animals_db")
public class AnimalFromDB {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalFromDB
     *
     * @return an instance of java.lang.String
     */
    @Path("/animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {

            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByID(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT a FROM Animal a where a.id=:id", Animal.class);
            query.setParameter("id", id);
            Animal animal = (Animal) (query.getSingleResult());
            return new Gson().toJson(animal);

        } finally {
            em.close();
        }
    }

    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByID(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT a FROM Animal a where a.type=:type", Animal.class);
            query.setParameter("type", type);
            try {
                Animal animal = (Animal) (query.getSingleResult());
                return new Gson().toJson(animal);
            } catch (NoResultException ex) {
                return type+" does not exist in DB";
            }
        } finally {
            em.close();
        }
    }
    
    @Path("/random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        EntityManager em = emf.createEntityManager();
        try {

            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            Random random = new Random();
            int randomID = random.nextInt(animals.size());
            return new Gson().toJson(animals.get(randomID));
        } finally {
            em.close();
        }
    }
    
}
