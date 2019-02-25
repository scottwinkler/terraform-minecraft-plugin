package com.cocoapebbles.terraform.provider;

import com.cocoapebbles.terraform.models.Model;
import com.cocoapebbles.terraform.models.ResourceData;
import com.cocoapebbles.terraform.models.cube.CubeResourceDataDAO;
import com.cocoapebbles.terraform.models.cube.CubeResourceData;
import com.cocoapebbles.terraform.controllers.BukkitController;
import com.cocoapebbles.terraform.models.cube.Cube;
import com.cocoapebbles.terraform.models.cube.CubeDimensions;
import com.cocoapebbles.terraform.controllers.models.Model;
import com.cocoapebbles.terraform.models.shape.CubeDimensions;
import com.cocoapebbles.terraform.models.shape.Shape;
import com.cocoapebbles.terraform.models.shape.ShapeResourceData;
import com.cocoapebbles.terraform.utility.Utility;
import org.bukkit.*;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.sun.javafx.tools.resource.DeployResource.Type.data;

public class ResourceCube implements Resource{


    public ArrayList<Block> getRegionBlocks(CubeDimensions cubeDimensions, Location location) {
        int length = cubeDimensions.getLengthX();
        int width = cubeDimensions.getWidthZ();
        int height = cubeDimensions.getHeightY();
        World w = BukkitController.getWorld();
        ArrayList<Block> blocks = new ArrayList<Block>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    Location blockLocation = location.clone().add(k,i,j);
                    blocks.add(w.getBlockAt(blockLocation));
                }
            }
        }
        return blocks;
    }

/*
    public Future<Model> create(ResourceData data) {
        String id = UUID.randomUUID().toString().substring(0,8);
        return create(data,id,true);
    }*/
    public Model create(ResourceData resourceData){

        ShapeResourceData shapeResourceData = (ShapeResourceData) resourceData;
        Shape shape = new Shape();

        CubeDimensions cubeDimensions = (CubeDimensions) shape.getShapeResourceData().getDimensions();
        Location location = shapeResourceData.getLocation();
        ArrayList<Block> blocks = getRegionBlocks(cubeDimensions,location);
        List<Material> previousData = blocks.stream().map(b->{return b.getBlockData().getMaterial();}).collect(Collectors.toList());
        shapeResourceData.setPreviousData(previousData);
        for(Block block : blocks){
            ProviderUtility.
        }
        ArrayList<Location> locationList = getLocationList(cubeDimensions,cubeResourceData.getLocation());
        ArrayList<String> oldMaterialList = getMaterialsList(locationList);
        ArrayList<String> newMaterialList = new ArrayList<String>();
        for(int i =0;i<locationList.size();i++){
            Location location = locationList.get(i);
            ProviderUtility.schedulePlacement(location, i, cubeResourceData.getMaterialId());
            newMaterialList.add(cubeResourceData.getMaterialId());
        }

        cube.setCubeDimensions(ProviderUtility.serializeCubeDimensions(cubeDimensions));
        cube.setId(id);
        cube.setLocation(ProviderUtility.serializeLocation(cubeResourceData.getLocation()));
        cube.setMaterialId(cubeResourceData.getMaterialId());
        if(isNew){
            cube.setPreviousData(oldMaterialList);
        } else{
            CubeResourceDataDAO cubeDAO = new CubeResourceDataDAO();
            Cube c = cubeDAO.getCube(id);
            cube.setPreviousData(c.getPreviousData());
        }
        cube.setCurrentData(newMaterialList);
        cube.setType("cube");

        return ProviderUtility.wrapFuture(cube,locationList.size());
    }

    public Model read(String cubeId) {
        Cube cube = new CubeResourceDataDAO().getCube(cubeId);
        ArrayList<Location> locationList = getLocationList(ProviderUtility.deserializeCubeDimensions(cube.getCubeDimensions()), ProviderUtility.deserializeLocation(cube.getLocation()));
        ArrayList<String> currentMaterials = getMaterialsList(locationList);

        Cube newCube = new Cube();
        newCube.setType("cube");
        newCube.setPreviousData(cube.getPreviousData());
        newCube.setMaterialId(cube.getMaterialId());
        newCube.setLocation(cube.getLocation());
        newCube.setId(cube.getId());
        newCube.setCubeDimensions(cube.getCubeDimensions());
        newCube.setCurrentData(currentMaterials);

        return newCube;
    }

    public Future<Model> update(ResourceData data, String cubeId) {
        CubeResourceData cubeResourceData = (CubeResourceData) data;
        Cube cube = (Cube) read(cubeId);
        delete(cubeId);
        return create(data,cube.getId(),false);
    }

    public Future<Model> delete(String cubeId) {
        Cube cube = (Cube) read(cubeId);
        ArrayList<Location> locationList = getLocationList(ProviderUtility.deserializeCubeDimensions(cube.getCubeDimensions()), ProviderUtility.deserializeLocation(cube.getLocation()));
        List<String> materialsList = cube.getPreviousData();
        for(int i =0;i<locationList.size();i++) {
            Location location = locationList.get(i);
            String blockType = materialsList.get(i);
            ProviderUtility.schedulePlacement(location, i, blockType);
        }
        return ProviderUtility.wrapFuture(cube,locationList.size());
    }


}
