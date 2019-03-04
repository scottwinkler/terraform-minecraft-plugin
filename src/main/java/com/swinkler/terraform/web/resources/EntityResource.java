package com.swinkler.terraform.web.resources;

import com.swinkler.terraform.models.DAOFactory;
import com.swinkler.terraform.models.entity.Entity;
import com.swinkler.terraform.models.entity.EntityDAO;
import com.swinkler.terraform.models.entity.EntityRequest;
import com.swinkler.terraform.provider.ResourceEntity;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/")
public class EntityResource {
    ResourceEntity resourceEntity = new ResourceEntity();
    EntityDAO entityDAO = DAOFactory.getEntityDAO();

    @Inject
    private Logger logger;
    @Inject
    private JavaPlugin p;

    @OPTIONS
    public Response getOptions(){
        return Response.status(200)
                .header("Access-Control-Allow-Origin","*")
                .header("Access-Control-Allow-Methods","GET, POST, PATCH, DELETE")
                .header("Access-Control-Allow-Headers","origin, content-type, accept").build();
    }

    @GET
    public Response listEntities( @DefaultValue("100") @QueryParam("limit") Integer limit){
        List<Entity> entities = entityDAO.listEntities(limit);
        GenericEntity<List<Entity>> genericEntity = new GenericEntity<>(entities);
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEntity(EntityRequest request) {
        Entity entity = resourceEntity.create(request);
        if(entity==null){
            return Response.status(400).build();
        }
        GenericEntity<Entity> genericEntity = new GenericEntity<>(entity);
        return Response.status(201).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntity(@PathParam("id") String entityId){
        Entity entity = resourceEntity.read(entityId);
        if(entity==null){
            return Response.status(404).header("Access-Control-Allow-Origin","*").build();
        }
        GenericEntity<Entity> genericEntity = new GenericEntity<>(entity);
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }



    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEntity(@PathParam("id") String entityId, EntityRequest request) {
        Entity entity = resourceEntity.update(entityId,request);
        if(entity==null){
            return Response.status(404).header("Access-Control-Allow-Origin","*").build();
        }
        GenericEntity<Entity> genericEntity = new GenericEntity<>(entity);
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEntity(@PathParam("id") String entityId) {
        Entity entity = resourceEntity.read(entityId);
        if(entity==null){
            return Response.status(404).header("Access-Control-Allow-Origin","*").build();
        }
        resourceEntity.delete(entityId);
        return Response.status(200).header("Access-Control-Allow-Origin","*").build();
    }
}
