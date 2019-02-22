package com.cocoapebbles.terraform.models.cylinder;

import org.bukkit.Location;

public class CylinderResourceData {
    private Location location;
    private String materialId;
    private CylinderDimensions cylinderDimensions;
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public CylinderDimensions getCylinderDimensions() {
        return cylinderDimensions;
    }

    public void setCylinderDimensions(CylinderDimensions cylinderDimensions) {
        this.cylinderDimensions = cylinderDimensions;
    }
}
