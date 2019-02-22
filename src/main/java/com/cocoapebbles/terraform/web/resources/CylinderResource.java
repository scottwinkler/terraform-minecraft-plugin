package com.cocoapebbles.terraform.web.resources;
/*
import com.cocoapebbles.terraform.models.cylinder.CylinderResourceDataDAO;
import com.cocoapebbles.terraform.models.cylinder.CylinderResourceData;
import com.cocoapebbles.terraform.provider.ResourceCylinder;
import com.cocoapebbles.terraform.utility.Utility;
import com.cocoapebbles.terraform.models.cylinder.Cylinder;
import com.cocoapebbles.terraform.models.cylinder.CylinderRequest;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@Path("/")
public class CylinderResource {
    public CylinderResourceData transformRequestData(CylinderRequest request){
        //Convert to CylinderResource
        CylinderResourceData cylinderResourceData = new CylinderResourceData();
        cylinderResourceData.setMaterialId(request.getMaterialId());
        cylinderResourceData.setLocation(Utility.deserializeLocation(request.getLocation()));
        int radius = request.getCylinderDimensions().get(0);
        int height = request.getCylinderDimensions().get(1);
        cylinderResourceData.setCylinderDimensions(new CylinderDimensions(radius,height));
        return cylinderResourceData;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCylinder(){

        return Response.status(200).header("Access-Control-Allow-Origin","*").entity("hello world").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCylinder(CylinderRequest request) {
        CylinderResourceData cylinderResourceData = transformRequestData(request);
        Future<Model> future = new ResourceCylinder().create(cylinderResourceData);
        Cylinder cylinder = (Cylinder) Utility.getFuture(future);
        CylinderResourceDataDAO dao = new CylinderResourceDataDAO(p);
        dao.createCylinder(cylinder);
        GenericEntity<Cylinder> genericEntity = new GenericEntity<Cylinder>(cylinder){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCylinder(@PathParam("id") String cylinderId){
        ResourceCylinder resourceCylinder = new ResourceCylinder();
        Cylinder cylinder = (Cylinder) resourceCylinder.read(cylinderId);
        CylinderResourceDataDAO cylinderDAO = new CylinderResourceDataDAO();
        cylinderDAO.updateCylinder(cylinder);

        GenericEntity<Cylinder> genericEntity = new GenericEntity<Cylinder>(cylinder){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }



    @PATCH
    @Path("/{id}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCylinder(@PathParam("id") String cylinderId, CylinderRequest request) {
        logger.info("updating cylinder");
        CylinderResourceData cylinderResourceData = transformRequestData(request);
        Future<Model> future = new ResourceCylinder().update(cylinderResourceData,cylinderId);
        Cylinder cylinder = (Cylinder) Utility.getFuture(future);
        CylinderResourceDataDAO dao = new CylinderResourceDataDAO(p);
        dao.updateCylinder(cylinder);
        GenericEntity<Cylinder> genericEntity = new GenericEntity<Cylinder>(cylinder){};
        return Response.status(200).header("Access-Control-Allow-Origin","*").entity(genericEntity).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCylinder(@PathParam("id") String cylinderId) {
        logger.info("deleting cylinder");
        Future<Model> future = new ResourceCylinder().delete(cylinderId);
        Cylinder cylinder = (Cylinder) Utility.getFuture(future);
        CylinderResourceDataDAO dao = new CylinderResourceDataDAO(p);
        dao.deleteCylinder(cylinder);
        return Response.status(200).header("Access-Control-Allow-Origin","*").build();
    }
}
*/