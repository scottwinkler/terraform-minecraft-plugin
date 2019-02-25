package com.cocoapebbles.terraform.serialization;

import com.cocoapebbles.terraform.enums.ResourceStatus;

public class SerializedShape implements Serializable{
    private String id;
    private SerializedShapeResourceData serializedShapeResourceData;
    private ResourceStatus resourceStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SerializedShapeResourceData getSerializedShapeResourceData() {
        return serializedShapeResourceData;
    }

    public void setSerializedShapeResourceData(SerializedShapeResourceData serializedShapeResourceData) {
        this.serializedShapeResourceData = serializedShapeResourceData;
    }

    public ResourceStatus getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(ResourceStatus resourceStatus) {
        this.resourceStatus = resourceStatus;
    }
}
