package com.swinkler.terraform.provider;

import com.swinkler.terraform.models.shape.Shape;
import com.swinkler.terraform.models.shape.ShapeRequest;
import com.swinkler.terraform.models.shape.ShapeType;

public class ResourceShape implements Resource<ShapeRequest,Shape> {

    public Resource<ShapeRequest,Shape> getResourceByShapeType(ShapeType shapeType){
        switch(shapeType){
            case Cube: return new ResourceCube();
            default: return null;
        }
    }

    public Resource<ShapeRequest,Shape> getResourceById(String shapeId){
        ShapeType shapeType = ShapeType.valueOf(shapeId.split("-")[0]);
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