package com.cocoapebbles.terraform.serialization;

import com.cocoapebbles.terraform.enums.ResourceStatus;
import com.cocoapebbles.terraform.models.entity.Entity;

public class SerializedEntity implements Serializable {
    private String id;
    private SerializedEntityResourceData serializedEntityResourceData;
    private ResourceStatus resourceStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SerializedEntityResourceData getSerializedEntityResourceData() {
        return serializedEntityResourceData;
    }

    public void setSerializedEntityResourceData(SerializedEntityResourceData serializedEntityResourceData) {
        this.serializedEntityResourceData = serializedEntityResourceData;
    }

    public ResourceStatus getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(ResourceStatus resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

}
