package com.cocoapebbles.terraform.models;

import org.bukkit.World;

public class Location {
    //public World w;
    public int x;
    public int y;
    public int z;
/*
    public Location(World w, int x, int y, int z) {
        this.w=w;
        this.x=x;
        this.y=y;
        this.z=z;
    }
*/

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
}
