package com.cocoapebbles.terraform.provider;
/*
import com.cocoapebbles.terraform.models.cube.CubeResourceDataDAO;
import com.cocoapebbles.terraform.models.cube.CubeResourceData;
import com.cocoapebbles.terraform.controllers.BukkitController;
import com.cocoapebbles.terraform.models.cube.Cube;
import com.cocoapebbles.terraform.models.cube.CubeDimensions;
import com.cocoapebbles.terraform.controllers.models.Model;
import com.cocoapebbles.terraform.utility.Utility;
import org.bukkit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

public class ResourceCube implements Resource{


    public ArrayList<Location> getLocationList(CubeDimensions cubeDimensions, Location location) {
        int length = cubeDimensions.getLengthX();
        int width = cubeDimensions.getWidthZ();
        int height = cubeDimensions.getHeightY();
        World w = BukkitController.getWorld();
        ArrayList<Location> locationList = new ArrayList<Location>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    locationList.add(new Location(w, location.getX()+k, location.getY()+i, location.getZ()+j));
                }
            }
        }
        return locationList;
    }

    public ArrayList<String> getMaterialsList(ArrayList<Location> locationList){
        ArrayList<String> materialsList = new ArrayList<String>();
        for (Location location: locationList){
            materialsList.add(ProviderUtility.checkBlock(location));
        }
        return materialsList;
    }

    public Future<Model> create(ResourceData data) {
        String id = UUID.randomUUID().toString().substring(0,8);
        return create(data,id,true);
    }
    public Future<Model> create(ResourceData data, String id, boolean isNew){
        CubeResourceData cubeResourceData = (CubeResourceData) data;

        Cube cube = new Cube();
        CubeDimensions cubeDimensions = cubeResourceData.getCubeDimensions();
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
*/