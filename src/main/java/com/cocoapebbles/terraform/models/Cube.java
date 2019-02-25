package com.cocoapebbles.terraform.models;

import java.util.List;

public class Cube extends Shape {
    public int xLength;
    public int yLength;
    public int zLength;
    public String materialId;

    private transient List<String> data;

    public void setData(List<String> data) {
      this.data = data;
    }

    public List<String> getData() {
      return this.data;
    }
}
