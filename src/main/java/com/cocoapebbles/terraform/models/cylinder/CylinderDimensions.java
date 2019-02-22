package com.cocoapebbles.terraform.models.cylinder;

public class CylinderDimensions {
    private int radius;
    private int height;

    public CylinderDimensions(int radius, int height){
        this.radius = radius;
        this.height = height;
    }
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
