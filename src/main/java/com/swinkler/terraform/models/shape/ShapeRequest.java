package com.swinkler.terraform.models.shape;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swinkler.terraform.models.location.Location;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import java.util.Map;

public class ShapeRequest {
    private Location location;
    private ShapeType shapeType;
    private String material;
    private Map<String,Integer> dimensions;

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

    public Map<String,Integer> getDimensions() {
        return dimensions;
    }

    public void setDimensions(Map<String,Integer> dimensions) {
        this.dimensions = dimensions;
    }

    @JsonIgnore
    public Material getBukkitMaterial(){
        return Material.valueOf(material);
    }

    @JsonIgnore
    public void setBukkitMaterial(Material material){
        this.material = material.name();
    }

    @JsonIgnore
    public org.bukkit.Location getBukkitLocation(){
        return location.getAsBukkitLocation();
    }

    @JsonIgnore
    public void setBukkitLocation(org.bukkit.Location bukkitLocation){
        location = new Location(bukkitLocation);
    }
}
