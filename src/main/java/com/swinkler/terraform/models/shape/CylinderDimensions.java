package com.swinkler.terraform.models.shape;

import java.util.Map;

public class CylinderDimensions {
    private int height;
    private int radius;

    public CylinderDimensions(Map<String,Integer> map){
        height = map.get("height");
        radius = map.get("radius");
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
