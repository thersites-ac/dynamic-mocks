package org.example.resource;

import org.example.mock.Mockable;
import org.example.model.Doggo;
import org.example.service.DoggoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/doggos")
@Component
public class DoggoResource {

    @Autowired
    private DoggoService doggoService;

    private static final Logger logger = LoggerFactory.getLogger(DoggoResource.class);

    @GET
    @Path("/{id}")
    public Doggo getDoggo(@PathParam("id") int id) {
        logger.info("getDoggo {}", id);
        return doggoService.getDoggo(id);
    }

    @GET
    @Mockable(handle = "getAllDoggos")
    public List<Doggo> getAllDoggos() {
        List<Doggo> doggos = doggoService.getAllDoggos();
        logger.info("getAllDoggos {}", doggos);
        return doggos;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Mockable(handle = "createDoggo")
    public int createDoggo(Doggo doggo) {
        logger.info("createDoggo {}", doggo);
        return doggoService.createDoggo(doggo);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateDoggo(@PathParam("id") int id, Doggo doggo) {
        logger.info("updateDoggo {}", id);
        doggoService.updateDoggo(id, doggo);
    }

    @DELETE
    @Path("/{id}")
    public void deleteDoggo(@PathParam("id") int id) {
        logger.info("deleteDoggo {}", id);
        doggoService.deleteDoggo(id);
    }

}
