package com.cocoapebbles.terraform.web.resources;
/*
import com.cocoapebbles.terraform.models.cube.CubeResourceDataDAO;
import com.cocoapebbles.terraform.models.cube.Cube;
import com.cocoapebbles.terraform.models.cube.CubeResourceData;
import com.cocoapebbles.terraform.models.cube.CubeRequest;
import com.cocoapebbles.terraform.controllers.models.Model;
import com.cocoapebbles.terraform.models.cube.CubeDimensions;
import com.cocoapebbles.terraform.provider.ResourceCube;
import com.cocoapebbles.terraform.utility.Utility;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@Path("/")
public class CubeResource {
    public CubeResourceData transformRequestData(CubeRequest request){
        //Convert to CubeResource
        CubeResourceData cubeResourceData = new CubeResourceData();
        cubeResourceData.setMaterialId(request.getMaterialId());
        cubeResourceData.setLocation(Utility.deserializeLocation(request.getLocation()));
        int height = request.getCubeDimensions().get(0);
        int width = request.getCubeDimensions().get(1);
        int length = request.getCubeDimensions().get(2);
        cubeResourceData.setCubeDimensions(new CubeDimensions(length,height,width));
        return cubeResourceData;
    }


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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCube(CubeRequest request) {
        CubeResourceData cubeResourceData = transformRequestData(request);
        Future<Model> future = new ResourceCube().create(cubeResourceData);
        Cube cube = (Cube) Utility.getFuture(future);
        CubeResourceDataDAO dao = new CubeResourceDataDAO(p);
        dao.createCube(cube);
        GenericEntity<Cube> genericEntity = new GenericEntity<Cube>(cube){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCube(@PathParam("id") String cubeId){
        ResourceCube resourceCube = new ResourceCube();
        Cube cube = (Cube) resourceCube.read(cubeId);
        CubeResourceDataDAO cubeDAO = new CubeResourceDataDAO();
        cubeDAO.updateCube(cube);

        GenericEntity<Cube> genericEntity = new GenericEntity<Cube>(cube){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }



    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCube(@PathParam("id") String cubeId, CubeRequest request) {
        logger.info("updating cube");
        CubeResourceData cubeResourceData = transformRequestData(request);
        Future<Model> future = new ResourceCube().update(cubeResourceData,cubeId);
        Cube cube = (Cube) Utility.getFuture(future);
        CubeResourceDataDAO dao = new CubeResourceDataDAO(p);
        dao.updateCube(cube);
        GenericEntity<Cube> genericEntity = new GenericEntity<Cube>(cube){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCube(@PathParam("id") String cubeId) {
        logger.info("deleting cube");
        Future<Model> future = new ResourceCube().delete(cubeId);
        Cube cube = (Cube) Utility.getFuture(future);
        CubeResourceDataDAO dao = new CubeResourceDataDAO(p);
        dao.deleteCube(cube);
        return Response.status(200).header("Access-Control-Allow-Origin","*").build();
    }
}
*/