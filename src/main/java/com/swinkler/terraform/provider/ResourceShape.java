package com.swinkler.terraform.provider;

import com.swinkler.terraform.models.shape.Shape;
import com.swinkler.terraform.models.shape.ShapeRequest;
import com.swinkler.terraform.models.shape.ShapeType;

public class ResourceShape implements Resource<ShapeRequest,Shape> {

    public Resource<ShapeRequest,Shape> getResourceByShapeType(ShapeType shapeType){
        switch(shapeType){
            case Cube: return new ResourceCube();
            case Cylinder: return new ResourceCylinder();
            default: return null;
        }
    }

    public Resource<ShapeRequest,Shape> getResourceById(String shapeId){
        String input = shapeId.split("-")[0];
        String capitalized = input.substring(0, 1).toUpperCase() + input.substring(1);
        ShapeType shapeType = ShapeType.valueOf(capitalized);
        return getResourceByShapeType(shapeType);
    }

    public Shape create(ShapeRequest shapeRequest) {
       return getResourceByShapeType(shapeRequest.getShapeType()).create(shapeRequest);
    }

    public Shape read(String shapeId) {
        return getResourceById(shapeId).read(shapeId);
    }

    public Shape update(String shapeId, ShapeRequest shapeRequest) {
        return getResourceByShapeType(shapeRequest.getShapeType()).update(shapeId,shapeRequest);
    }

    public void delete(String shapeId) {
       getResourceById(shapeId).delete(shapeId);
    }
}