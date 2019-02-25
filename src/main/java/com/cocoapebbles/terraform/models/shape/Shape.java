package com.cocoapebbles.terraform.models.shape;

import com.cocoapebbles.terraform.enums.ResourceStatus;
import com.cocoapebbles.terraform.models.Model;

public class Shape implements Model {
    private String id;
    private ShapeResourceData shapeResourceData;
    private ResourceStatus resourceStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShapeResourceData getShapeResourceData() {
        return shapeResourceData;
    }

    public void setShapeResourceData(ShapeResourceData shapeResourceData) {
        this.shapeResourceData = shapeResourceData;
    }

    public ResourceStatus getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(ResourceStatus resourceStatus) {
        this.resourceStatus = resourceStatus;
    }
}
