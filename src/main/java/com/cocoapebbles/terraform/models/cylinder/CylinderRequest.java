package com.cocoapebbles.terraform.models.cylinder;

import java.util.List;

public class CylinderRequest {
    private List<Integer> cylinderDimensions;
    private List<Integer> location;
    private String materialId;

    public List<Integer> getCylinderDimensions() {
        return cylinderDimensions;
    }

    public void setCylinderDimensions(List<Integer> cylinderDimensions) {
        this.cylinderDimensions = cylinderDimensions;
    }

    public List<Integer> getLocation() {
        return location;
    }

    public void setLocation(List<Integer> location) {
        this.location = location;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
}

