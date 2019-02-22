package com.cocoapebbles.terraform.serialization;

import com.cocoapebbles.terraform.models.entity.EntityResourceData;

import java.util.List;
public class SerializedEntityResourceData {
    private List<Integer> location;
    private String entityType;
    private String customName;


    public SerializedEntityResourceData(){}

    public List<Integer> getLocation() {
        return location;
    }

    public void setLocation(List<Integer> location) {
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
}
