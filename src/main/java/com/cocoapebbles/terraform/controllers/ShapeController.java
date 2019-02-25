package com.cocoapebbles.terraform.controllers;

import com.cocoapebbles.terraform.models.Shape;
import com.cocoapebbles.terraform.models.Cube;

import java.util.logging.Logger;

public class ShapeController implements Controller<Shape> {
    private static ShapeController instance = null;
    private static CubeController cubeController = CubeController.getInstance();

    public static synchronized ShapeController getInstance() {
        if ( instance == null ) {
            instance = new ShapeController();
        }
        return instance;
    }

    public Shape create(Shape shape) {
      if (shape instanceof Cube) {
        return cubeController.create((Cube)shape);
      }
      return null;
    }

    public Shape get(String shapeId) {
      if (shapeId.startsWith("cube")) {
        return cubeController.get(shapeId);
      }
      return null;
    }

    public Shape update(String shapeId, Shape shape) {
      if (shape instanceof Cube) {
        return cubeController.update(shapeId, (Cube)shape);
      } 
      return null;
    }

    public Shape delete(String shapeId) {
      if (shapeId.startsWith("cube")) {
        return cubeController.delete(shapeId);
      } 
      return null;
    }
}
