package com.cocoapebbles.terraform.models.shape;

import com.cocoapebbles.terraform.enums.ShapeType;
import com.cocoapebbles.terraform.models.ResourceData;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public class ShapeResourceData implements ResourceData {
    private Location location;
    private ShapeType shapeType;
    private Dimensions dimensions;
    private List<Material> previousData;
    private Material material;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public List<Material> getPreviousData() {
        return previousData;
    }

    public void setPreviousData(List<Material> previousData) {
        this.previousData = previousData;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
