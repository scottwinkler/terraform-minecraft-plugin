package com.swinkler.terraform.web.resources;

import com.swinkler.terraform.models.DAOFactory;
import com.swinkler.terraform.models.shape.Shape;
import com.swinkler.terraform.models.shape.ShapeDAO;
import com.swinkler.terraform.models.shape.ShapeRequest;
import com.swinkler.terraform.provider.ResourceShape;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/")
public class ShapeResource {
    ResourceShape resourceShape = new ResourceShape();
    ShapeDAO shapeDAO = DAOFactory.getShapeDAO();

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response listShapes( @DefaultValue("100") @QueryParam("limit") Integer limit){
        logger.info("in list");
        List<Shape> shapes = shapeDAO.listShapes(limit);
        GenericEntity<List<Shape>> genericShape = new GenericEntity<List<Shape>>(shapes){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericShape).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createShape(ShapeRequest request) {
        logger.info("in create");
        //ShapeRequest request = (ShapeRequest) o;
        Shape shape = resourceShape.create(request);
        if(shape==null){
            return Response.status(400).build();
        }
        GenericEntity<Shape> genericShape = new GenericEntity<Shape>(shape){};
        return Response.status(201).header("Access-Control-Allow-Origin","*").entity(genericShape).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShape(@PathParam("id") String shapeId){
        Shape shape = resourceShape.read(shapeId);
        if(shape==null){
            return Response.status(404).header("Access-Control-Allow-Origin","*").build();
        }
        GenericEntity<Shape> genericShape = new GenericEntity<Shape>(shape){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericShape).build();
    }



    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShape(@PathParam("id") String shapeId, ShapeRequest request) {
        Shape shape = resourceShape.update(shapeId,request);
        if(shape==null){
            return Response.status(404).header("Access-Control-Allow-Origin","*").build();
        }
        GenericEntity<Shape> genericShape = new GenericEntity<Shape>(shape){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericShape).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteShape(@PathParam("id") String shapeId) {
        Shape shape = resourceShape.read(shapeId);
        if(shape==null){
            return Response.status(404).header("Access-Control-Allow-Origin","*").build();
        }
        resourceShape.delete(shapeId);
        return Response.status(200).header("Access-Control-Allow-Origin","*").build();
    }
}