package com.cocoapebbles.terraform.web.resources;

import com.cocoapebbles.terraform.models.shape.Shape;
import com.cocoapebbles.terraform.models.shape.ShapeResourceData;
import com.cocoapebbles.terraform.provider.ProviderUtility;
import com.cocoapebbles.terraform.serialization.SerializationUtility;
import com.cocoapebbles.terraform.serialization.SerializedShape;
import com.cocoapebbles.terraform.serialization.SerializedShapeResourceData;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericShape;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@Path("/")
public class ShapeResource {
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
    public Response createShape(SerializedShapeResourceData request) {
        logger.info("in create");
        ShapeResourceData shapeResourceData = SerializationUtility.deserializeShapeResourceData(request);
        Shape shape = ResourceShape.create(shapeResourceData);
        SerializedShape serializedShape = SerializationUtility.serializeShape(shape);
        GenericShape<SerializedShape> genericShape = new GenericShape<SerializedShape>(serializedShape){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").shape(genericShape).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShape(@PathParam("id") String shapeId){
        ProviderUtility.getResourceByName("cube").
        Shape shape = ResourceShape.read(shapeId);
        ShapeResourceData shapeResourceData = shape.getShapeResourceData();
        SerializedShape serializedShape = SerializationUtility.serializeShape(shape);
        GenericShape<SerializedShape> genericShape = new GenericShape<SerializedShape>(serializedShape){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").shape(genericShape).build();
    }



    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShape(@PathParam("id") String shapeId, SerializedShapeResourceData request) {
        ShapeResourceData shapeResourceData = SerializationUtility.deserializeResourceData(request);
        Shape shape = DAOFactory.getShapeDAO().getShape(shapeId);
        shape.setShapeResourceData(shapeResourceData);
        shape = ResourceShape.update(shape);
        SerializedShape serializedShape = SerializationUtility.serializeShape(shape);
        GenericShape<SerializedShape> genericShape = new GenericShape<SerializedShape>(serializedShape){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").shape(genericShape).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteShape(@PathParam("id") String shapeId) {
        ResourceShape.delete(shapeId);
        return Response.status(200).header("Access-Control-Allow-Origin","*").build();
    }
}
