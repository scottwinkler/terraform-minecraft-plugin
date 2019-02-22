package com.cocoapebbles.terraform.serialization;

import com.cocoapebbles.terraform.controllers.BukkitController;
//import com.cocoapebbles.terraform.models.cube.CubeDimensions;
import com.cocoapebbles.terraform.models.cylinder.CylinderDimensions;
import com.cocoapebbles.terraform.models.entity.Entity;
import com.cocoapebbles.terraform.models.entity.EntityResourceData;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class SerializationUtility {
  /*  public static CubeDimensions deserializeCubeDimensions(List<Integer> dimensions){
        return new CubeDimensions(dimensions.get(0), dimensions.get(1), dimensions.get(2));
    }

    public static List<Integer> serializeCubeDimensions(CubeDimensions dimensions){
        List<Integer> dimension = new ArrayList<>();
        dimension.add(dimensions.getHeightY());
        dimension.add(dimensions.getWidthZ());
        dimension.add(dimensions.getLengthX());
        return dimension;
    }
*/

  public static SerializedEntity serializeEntity(Entity entity){
      SerializedEntity serializedEntity = new SerializedEntity();
      serializedEntity.setId(entity.getId());
      serializedEntity.setResourceStatus(entity.getResourceStatus());
      serializedEntity.setSerializedEntityResourceData(SerializationUtility.serializeResourceData(entity.getEntityResourceData()));
      return serializedEntity;
  }

  public static Entity deserializeEntity(SerializedEntity serializedEntity){
      Entity entity = new Entity();
      entity.setId(serializedEntity.getId());
      entity.setEntityResourceData(SerializationUtility.deserializeResourceData(serializedEntity.getSerializedEntityResourceData()));
      entity.setResourceStatus(serializedEntity.getResourceStatus());
      return entity;
  }

  public static SerializedEntityResourceData serializeResourceData(EntityResourceData entityResourceData) {
      List<Integer> location = SerializationUtility.serializeLocation(entityResourceData.getLocation());
      String entityType = entityResourceData.getEntityType().name();
      String customName = entityResourceData.getCustomName();
    SerializedEntityResourceData serializedEntityResourceData = new SerializedEntityResourceData();
    serializedEntityResourceData.setLocation(location);
    serializedEntityResourceData.setCustomName(customName);
    serializedEntityResourceData.setEntityType(entityType);
    return serializedEntityResourceData;
  }
    public static EntityResourceData deserializeResourceData(SerializedEntityResourceData serializedEntityResourceData) {
      EntityResourceData entityResourceData = new EntityResourceData();
      entityResourceData.setLocation(SerializationUtility.deserializeLocation(serializedEntityResourceData.getLocation()));
      entityResourceData.setCustomName(serializedEntityResourceData.getCustomName());
      entityResourceData.setEntityType(EntityType.valueOf(serializedEntityResourceData.getEntityType()));
       return entityResourceData;
    }

      public static CylinderDimensions deserializeCylinderDimensions(List<Integer> dimensions){
        return new CylinderDimensions(dimensions.get(0), dimensions.get(1));
    }

    public static List<Integer> serializeCylinderDimensions(CylinderDimensions dimensions){
        List<Integer> dimension = new ArrayList<>();
        dimension.add(dimensions.getRadius());
        dimension.add(dimensions.getHeight());
        return dimension;
    }


    public static Location deserializeLocation(List<Integer> location){
        return new Location(BukkitController.getWorld(),(double) location.get(0),(double) location.get(1), (double) location.get(2));
    }

    public static List<Integer> serializeLocation(Location location){
        List<Integer> output = new ArrayList<>();
        output.add(location.getBlockX());
        output.add(location.getBlockY());
        output.add(location.getBlockZ());
        return output;
    }
}
