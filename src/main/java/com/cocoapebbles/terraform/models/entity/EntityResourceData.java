package com.cocoapebbles.terraform.models.entity;

import com.cocoapebbles.terraform.models.ResourceData;
import com.cocoapebbles.terraform.serialization.SerializedEntityResourceData;
import com.cocoapebbles.terraform.serialization.SerializationUtility;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class EntityResourceData implements ResourceData {
    private Location location;
    private EntityType entityType;
    private String customName;

    public EntityResourceData(){}

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

}
