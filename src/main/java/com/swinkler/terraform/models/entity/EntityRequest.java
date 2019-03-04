package com.swinkler.terraform.models.entity;

import com.swinkler.terraform.models.location.Location;
import org.bukkit.entity.EntityType;

public class EntityRequest {
    private Location location;
    private String entityType;
    private String customName;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public EntityType getBukkitEntityType(){
        return EntityType.valueOf(entityType);
    }

    public void setBukkitEntityType(EntityType bukkitEntityType){
        entityType = bukkitEntityType.name();
    }

    public org.bukkit.Location getBukkitLocation(){
        return location.getAsBukkitLocation();
    }

    public void setBukkitLocation(org.bukkit.Location bukkitLocation){
        location = new Location(bukkitLocation);
    }
}
