package com.swinkler.terraform.models.shape;
import java.util.Map;
public class CubeDimensions  {
    private Integer lengthX;
    private Integer heightY;
    private Integer widthZ;

    public CubeDimensions(Map<String,Integer> map){
     lengthX = map.get("lengthX");
     heightY = map.get("heightY");
     widthZ = map.get("widthZ");
    }
    public Integer getLengthX() {
        return lengthX;
    }

    public void setLengthX(Integer lengthX) {
        this.lengthX = lengthX;
    }

    public Integer getHeightY() {
        return heightY;
    }

    public void setHeightY(Integer heightY) {
        this.heightY = heightY;
    }

    public Integer getWidthZ() {
        return widthZ;
    }

    public void setWidthZ(Integer widthZ) {
        this.widthZ = widthZ;
    }
}
