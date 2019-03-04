package com.swinkler.terraform.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swinkler.terraform.models.ResourceStatus;
import com.swinkler.terraform.models.location.Location;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class Entity  {
    String id;
    Location location;
    String entityType;
    String customName;
    ResourceStatus status;

    public Entity(){}

    public Entity(EntityRequest entityRequest){
        id = "entity-"+ UUID.randomUUID().toString().substring(0,8);
        this.location = entityRequest.getLocation();
        this.entityType = entityRequest.getEntityType();
        this.customName = entityRequest.getCustomName();
        status = ResourceStatus.Initializing;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    @JsonIgnore
    public EntityType getBukkitEntityType(){
        return EntityType.valueOf(entityType);
    }

    @JsonIgnore
    public void setBukkitEntityType(EntityType bukkitEntityType){
        entityType = bukkitEntityType.name();
    }

    @JsonIgnore
    public org.bukkit.Location getBukkitLocation(){
        return location.getAsBukkitLocation();
    }

    @JsonIgnore
    public void setBukkitLocation(org.bukkit.Location bukkitLocation){
        location = new Location(bukkitLocation);
    }

}
