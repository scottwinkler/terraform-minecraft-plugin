package com.cocoapebbles.terraform.serialization;

import com.cocoapebbles.terraform.controllers.BukkitController;
//import com.cocoapebbles.terraform.models.cube.CubeDimensions;
import com.cocoapebbles.terraform.models.cylinder.CylinderDimensions;
import com.cocoapebbles.terraform.models.entity.Entity;
import com.cocoapebbles.terraform.models.entity.EntityResourceData;
import com.cocoapebbles.terraform.models.shape.ShapeResourceData;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class SerializationUtility {
    public static Location deserializeLocation(List<Integer> location) {
        return new Location(BukkitController.getWorld(), (double) location.get(0), (double) location.get(1), (double) location.get(2));
    }

    public static List<Integer> serializeLocation(Location location) {
        List<Integer> output = new ArrayList<>();
        output.add(location.getBlockX());
        output.add(location.getBlockY());
        output.add(location.getBlockZ());
        return output;
    }
}
