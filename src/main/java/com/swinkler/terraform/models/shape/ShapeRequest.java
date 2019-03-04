package com.swinkler.terraform.models.shape;

import com.swinkler.terraform.models.location.Location;
import org.bukkit.Material;

public class ShapeRequest {
    private Location location;
    private ShapeType shapeType;
    private String material;
    private Object dimensions;

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Object getDimensions() {
        return dimensions;
    }

    public void setDimensions(Object dimensions) {
        this.dimensions = dimensions;
    }

    public Material getBukkitMaterial(){
        return Material.valueOf(material);
    }

    public void setBukkitMaterial(Material material){
        this.material = material.name();
    }

    public org.bukkit.Location getBukkitLocation(){
        return location.getAsBukkitLocation();
    }

    public void setBukkitLocation(org.bukkit.Location bukkitLocation){
        location = new Location(bukkitLocation);
    }
}
