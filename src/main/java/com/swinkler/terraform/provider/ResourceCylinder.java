package com.swinkler.terraform.provider;
/*
import com.cocoapebbles.terraform.models.cylinder.CylinderResourceDataDAO;
import com.cocoapebbles.terraform.models.cylinder.CylinderResourceData;
import com.cocoapebbles.terraform.controllers.BukkitController;
import com.cocoapebbles.terraform.models.cylinder.Cylinder;
import com.cocoapebbles.terraform.models.cylinder.CylinderDimensions;
import com.cocoapebbles.terraform.controllers.models.Model;
import com.cocoapebbles.terraform.utility.Utility;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

public class ResourceCylinder implements Resource {
    public ArrayList<Location> getLocationList(CylinderDimensions cylinderDimensions, Location location) {
        int radius = cylinderDimensions.getRadius();
        int height = cylinderDimensions.getHeight();
        int x = radius - 1;
        int y = 0;
        int dx = 1;
        int dy = 1;
        int err = dx - (radius * 2);
        World w = BukkitController.getWorld();
        ArrayList<Location> locationList = new ArrayList<Location>();
        while (x >= y) {
            for (int k = 0; k<height; k++) {
                locationList.add(new Location(w, location.getX()+x, location.getY()+k, location.getZ()+y));
                locationList.add(new Location(w, location.getX()+y, location.getY()+k, location.getZ()+x));
                locationList.add(new Location(w, location.getX()-y, location.getY()+k, location.getZ()+x));
                locationList.add(new Location(w, location.getX()-x, location.getY()+k, location.getZ()+y));
                locationList.add(new Location(w, location.getX()-x, location.getY()+k, location.getZ()-y));
                locationList.add(new Location(w, location.getX()-y, location.getY()+k, location.getZ()-x));
                locationList.add(new Location(w, location.getX()+y, location.getY()+k, location.getZ()-x));
                locationList.add(new Location(w, location.getX()+x, location.getY()+k, location.getZ()-y));
            }
            if (err <= 0) {
                y++;
                err += dy;
                dy += 2;
            }

            if (err > 0) {
                x--;
                dx += 2;
                err += dx - (radius * 2);
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
        CylinderResourceData cylinderResourceData = (CylinderResourceData) data;

        Cylinder cylinder = new Cylinder();
        CylinderDimensions cylinderDimensions = cylinderResourceData.getCylinderDimensions();
        ArrayList<Location> locationList = getLocationList(cylinderDimensions, cylinderResourceData.getLocation());
        ArrayList<String> oldMaterialList = getMaterialsList(locationList);
        ArrayList<String> newMaterialList = new ArrayList<String>();
        for(int i =0;i<locationList.size();i++){
            Location location = locationList.get(i);
            ProviderUtility.schedulePlacement(location, i, cylinderResourceData.getMaterialId());
            newMaterialList.add(cylinderResourceData.getMaterialId());
        }
        cylinder.setCylinderDimensions(cylinderDimensions);
        cylinder.setId(id);
        cylinder.setLocation(ProviderUtility.serializeLocation(cylinderResourceData.getLocation()));
        cylinder.setMaterialId(cylinderResourceData.getMaterialId());
        if(isNew){
            cylinder.setPreviousData(oldMaterialList);
        } else{
            CylinderResourceDataDAO cylinderDAO = new CylinderResourceDataDAO();
            Cylinder c = cylinderDAO.getCylinder(id);
            cylinder.setPreviousData(c.getPreviousData());
        }
        cylinder.setCurrentData(newMaterialList);
        cylinder.setType("cylinder");

        return ProviderUtility.wrapFuture(cylinder,locationList.size());
    }

    public Model read(String cylinderId) {
        Cylinder cylinder = new CylinderResourceDataDAO().getCylinder(cylinderId);
        ArrayList<Location> locationList = getLocationList(cylinder.getCylinderDimensions(), ProviderUtility.deserializeLocation(cylinder.getLocation()));
        ArrayList<String> currentMaterials = getMaterialsList(locationList);

        Cylinder newCylinder = new Cylinder();
        newCylinder.setType("cylinder");
        newCylinder.setPreviousData(cylinder.getPreviousData());
        newCylinder.setMaterialId(cylinder.getMaterialId());
        newCylinder.setLocation(cylinder.getLocation());
        newCylinder.setId(cylinder.getId());
        newCylinder.setCylinderDimensions(cylinder.getCylinderDimensions());
        newCylinder.setCurrentData(currentMaterials);

        return newCylinder;
    }

    public Future<Model> update(ResourceData data, String cylinderId) {
        CylinderResourceData cylinderResourceData = (CylinderResourceData) data;
        Cylinder cylinder = (Cylinder) read(cylinderId);
        delete(cylinderId);
        return create(data,cylinder.getId(),false);
    }

    public Future<Model> delete(String cylinderId) {
        Cylinder cylinder = (Cylinder) read(cylinderId);
        ArrayList<Location> locationList = getLocationList(cylinder.getCylinderDimensions(), ProviderUtility.deserializeLocation(cylinder.getLocation()));
        List<String> materialsList = cylinder.getPreviousData();
        for(int i =0;i<locationList.size();i++) {
            Location location = locationList.get(i);
            String blockType = materialsList.get(i);
            ProviderUtility.schedulePlacement(location, i, blockType);
        }
        return ProviderUtility.wrapFuture(cylinder,locationList.size());
    }
}
*/