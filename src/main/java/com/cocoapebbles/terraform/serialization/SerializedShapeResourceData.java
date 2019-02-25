package com.cocoapebbles.terraform.serialization;

import com.cocoapebbles.terraform.enums.ShapeType;

import java.util.List;

public class SerializedShapeResourceData implements Serializable{
    private List<Integer> location;
    private ShapeType shapeType;
    private List<Integer> dimensions;
    private List<String> previousData;
    private List<String> currentData;
    private String material;

    public List<Integer> getLocation() {
        return location;
    }

    public void setLocation(List<Integer> location) {
        this.location = location;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public List<Integer> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Integer> dimensions) {
        this.dimensions = dimensions;
    }

    public List<String> getPreviousData() {
        return previousData;
    }

    public void setPreviousData(List<String> previousData) {
        this.previousData = previousData;
    }

    public List<String> getCurrentData() {
        return currentData;
    }

    public void setCurrentData(List<String> currentData) {
        this.currentData = currentData;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
