package com.cocoapebbles.terraform.controllers;

import com.cocoapebbles.terraform.models.Cube;

import java.util.logging.Logger;

public class CubeController implements Controller<Cube> {
    private static CubeController instance = null;

    public static synchronized CubeController getInstance() {
        if ( instance == null ) {
            instance = new CubeController();
        }
        return instance;
    }

    public Cube get(String cubeId) {
      return null;
    }

    public Cube create(Cube cube) {
      return null;
    }

    public Cube update(String cubeId, Cube cube) {
      return null;
    }

    public Cube delete(String cubeId) {
        return null;
    }
  
}
