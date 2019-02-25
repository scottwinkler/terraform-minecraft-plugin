package com.cocoapebbles.terraform.provider;

import com.cocoapebbles.terraform.controllers.BukkitController;
import com.cocoapebbles.terraform.enums.ResourceStatus;
import com.cocoapebbles.terraform.models.DAOFactory;
import com.cocoapebbles.terraform.models.Cube;
import com.cocoapebbles.terraform.models.Location;
//import com.cocoapebbles.terraform.models.entity.EntityDAO;
//import com.cocoapebbles.terraform.models.entity.EntityResourceData;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.UUID;

public class ResourceCube implements Resource {

    public static ArrayList<org.bukkit.Location> getLocationList(Cube cube) {
        Location location = cube.location;
        int length = cube.xLength;
        int width = cube.zLength;
        int height = cube.yLength;
        World w = BukkitController.getWorld();
        ArrayList<org.bukkit.Location> locationList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    locationList.add(new org.bukkit.Location(w, location.getX()+k, location.getY()+i, location.getZ()+j));
                }
            }
        }
        return locationList;
    }

    public static ArrayList<String> getMaterialsList(ArrayList<org.bukkit.Location> locationList){
        ArrayList<String> materialsList = new ArrayList<String>();
        for (org.bukkit.Location location: locationList){
            materialsList.add(ProviderUtility.checkBlock(location));
        }
        return materialsList;
    }

    public static Cube create(Cube cube) throws IllegalStateException{
        cube.id = "cube-" + UUID.randomUUID().toString().substring(0,8);
        cube.status = ResourceStatus.Initializing; 
        ArrayList<org.bukkit.Location> locationList = getLocationList(cube);
        ArrayList<String> oldMaterialList = getMaterialsList(locationList);
        ArrayList<String> newMaterialList = new ArrayList<String>();
        for(int i =0;i<locationList.size();i++){
            org.bukkit.Location location = locationList.get(i);
            ProviderUtility.schedulePlacement(location, i, cube.materialId);
            newMaterialList.add(cube.materialId);
        }

        cube.setData(newMaterialList);

        DAOFactory.getCubeDAO().saveCube(cube);
        return cube;
    }
    public static Cube read(String cubeId) {
/*
        EntityDAO entityDAO = DAOFactory.getEntityDAO();
        Entity entity = entityDAO.getEntity(entityId);
        EntityResourceData entityResourceData = entity.getEntityResourceData();
        World world = BukkitController.getWorld();
        String customName = entityResourceData.getCustomName();
        LivingEntity livingEntity = getLivingEntityByCustomName(customName);
        if(livingEntity!=null){
            entityResourceData.setLocation(livingEntity.getLocation());
            entity.setEntityResourceData(entityResourceData);
            entityDAO.updateEntity(entity);
        } else{
            entityDAO.deleteEntity(entity);
        }
        return entity;
*/
        return null;
    }

    public static Cube update(Cube entity) {
/*
        LivingEntity livingEntity = getLivingEntityByCustomName(entity.getEntityResourceData().getCustomName());
        if(livingEntity!=null){
            EntityResourceData entityResourceData = entity.getEntityResourceData();
            livingEntity.teleport(entityResourceData.getLocation());
        }
        return entity;
*/
        return null;
    }


    public static void delete(String cubeId) {
/*
        EntityDAO entityDAO = DAOFactory.getEntityDAO();
        Entity entity = entityDAO.getEntity(entityId);
        String customName = entity.getEntityResourceData().getCustomName();
        LivingEntity livingEntity = getLivingEntityByCustomName(customName);
        if(livingEntity!=null){
            livingEntity.remove();
        }
        entityDAO.deleteEntity(entity);
*/
    }
}
