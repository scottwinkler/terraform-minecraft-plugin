package com.cocoapebbles.terraform.web.resources;
import com.cocoapebbles.terraform.models.DAOFactory;
import com.cocoapebbles.terraform.models.entity.Entity;
import com.cocoapebbles.terraform.models.entity.EntityResourceData;
import com.cocoapebbles.terraform.provider.ResourceEntity;
import com.cocoapebbles.terraform.serialization.SerializationUtility;
import com.cocoapebbles.terraform.serialization.SerializedEntity;
import com.cocoapebbles.terraform.serialization.SerializedEntityResourceData;
import com.google.gson.Gson;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/")
public class EntityResource {
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
    public Response getHello(){
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity("hello world").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEntity(SerializedEntityResourceData request) {
        logger.info("in create");
        EntityResourceData entityResourceData = SerializationUtility.deserializeResourceData(request);
        Entity entity = ResourceEntity.create(entityResourceData);
        SerializedEntity serializedEntity = SerializationUtility.serializeEntity(entity);
        GenericEntity<SerializedEntity> genericEntity = new GenericEntity<SerializedEntity>(serializedEntity){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntity(@PathParam("id") String entityId){
        Entity entity = ResourceEntity.read(entityId);
        EntityResourceData entityResourceData = entity.getEntityResourceData();
        SerializedEntity serializedEntity = SerializationUtility.serializeEntity(entity);
        GenericEntity<SerializedEntity> genericEntity = new GenericEntity<SerializedEntity>(serializedEntity){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }



    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEntity(@PathParam("id") String entityId, SerializedEntityResourceData request) {
        EntityResourceData entityResourceData = SerializationUtility.deserializeResourceData(request);
        Entity entity = DAOFactory.getEntityDAO().getEntity(entityId);
        entity.setEntityResourceData(entityResourceData);
        entity = ResourceEntity.update(entity);
        SerializedEntity serializedEntity = SerializationUtility.serializeEntity(entity);
        GenericEntity<SerializedEntity> genericEntity = new GenericEntity<SerializedEntity>(serializedEntity){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEntity(@PathParam("id") String entityId) {
        ResourceEntity.delete(entityId);
        return Response.status(200).header("Access-Control-Allow-Origin","*").build();
    }
}
