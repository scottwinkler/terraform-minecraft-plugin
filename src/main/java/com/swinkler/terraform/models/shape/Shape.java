package com.swinkler.terraform.models.shape;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swinkler.terraform.models.ResourceStatus;
import com.swinkler.terraform.models.location.Location;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Map;

public class Shape {
    private String id;
    private Location location;
    private ShapeType shapeType;
    private String material;
    private Map<String,Integer> dimensions;
    private List<String> previousData;
    private ResourceStatus status;

    public Shape(){}

    public Shape(ShapeRequest shapeRequest){
        status = ResourceStatus.Initializing;
        dimensions = shapeRequest.getDimensions();
        material = shapeRequest.getMaterial();
        shapeType = shapeRequest.getShapeType();
        location = shapeRequest.getLocation();
        String shapeId = shapeType.name().toLowerCase() +"-" +UUID.randomUUID().toString().substring(0, 8);
        id = shapeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<String> getPreviousData() {
        return previousData;
    }

    public void setPreviousData(List<String> previousData) {
        this.previousData = previousData;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
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
    public List<Material> getBukkitPreviousData(){
        ArrayList<Material> bukkitPreviousData = new ArrayList<>();
        for(String s: previousData){
            bukkitPreviousData.add(Material.valueOf(s));
        }
        return bukkitPreviousData;
    }

    @JsonIgnore
    public void setBukkitPreviousData(List<Material> bukkitPreviousData){
        ArrayList<String> previousData = new ArrayList<>();
        for (Material m : bukkitPreviousData){
            previousData.add(m.name());
        }
        this.previousData = previousData;
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
