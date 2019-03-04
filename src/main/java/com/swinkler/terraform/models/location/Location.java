package com.swinkler.terraform.models.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bukkit.Bukkit;

public class Location {
    private int x;
    private int y;
    private int z;
    private String world;

    public Location(){}

    public Location(org.bukkit.Location bukkitLocation){
        x = bukkitLocation.getBlockX();
        y = bukkitLocation.getBlockY();
        z = bukkitLocation.getBlockZ();
        world = bukkitLocation.getWorld().getName();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    @JsonIgnore
    public org.bukkit.Location getAsBukkitLocation(){
        return new org.bukkit.Location(Bukkit.getWorld(world),(double)x,(double)y,(double)z);
    }
}
