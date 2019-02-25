package com.cocoapebbles.terraform.web.resources;

import com.cocoapebbles.terraform.models.Shape;
import com.cocoapebbles.terraform.models.Cube;
import com.cocoapebbles.terraform.controllers.ShapeController;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@Path("/")
public class ShapeResource {
    private static final ShapeController controller = ShapeController.getInstance();

    @Inject
    private Logger logger;
    @OPTIONS
    public Response getOptions(){
        return Response.status(200)
                .header("Access-Control-Allow-Origin","*")
                .header("Access-Control-Allow-Methods","GET, POST, PATCH, DELETE")
                .header("Access-Control-Allow-Headers","origin, content-type, accept").build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Shape shape) {
      System.out.println("hello");
      logger.info("hello");
      controller.create(shape);
      return Response.status(200).header("Access-Control-Allow-Origin","*").entity(shape).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String shapeId){
      Shape shape = controller.get(shapeId);
      return Response.status(200).header("Access-Control-Allow-Origin","*").entity(shape).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String shapeId, Shape shape) {
        controller.update(shapeId, shape);
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(shape).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String shapeId) {
        controller.delete(shapeId);
        return Response.status(200).header("Access-Control-Allow-Origin","*").build();
    }
}
