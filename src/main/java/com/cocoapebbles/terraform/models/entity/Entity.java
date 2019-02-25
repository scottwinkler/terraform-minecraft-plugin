package com.cocoapebbles.terraform.models.entity;

import com.cocoapebbles.terraform.enums.ResourceStatus;
import com.cocoapebbles.terraform.models.Model;

import java.util.UUID;

public class Entity implements Model {
    String id;
    EntityResourceData entityResourceData;
    ResourceStatus resourceStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EntityResourceData getEntityResourceData() {
        return entityResourceData;
    }

    public void setEntityResourceData(EntityResourceData entityResourceData) {
        this.entityResourceData = entityResourceData;
    }

    public ResourceStatus getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(ResourceStatus resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

}
