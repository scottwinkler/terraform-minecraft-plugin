package com.cocoapebbles.terraform.models.cube;

import java.util.List;

public class CubeRequest {
    private List<Integer> cubeDimensions;
    private List<Integer> location;
    private String materialId;

    public List<Integer> getCubeDimensions() {
        return cubeDimensions;
    }

    public void setCubeDimensions(List<Integer> cubeDimensions) {
        this.cubeDimensions = cubeDimensions;
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
