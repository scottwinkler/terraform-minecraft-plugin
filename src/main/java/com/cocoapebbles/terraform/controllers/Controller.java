package com.cocoapebbles.terraform.controllers;

public interface Controller<SHAPE> {
    public SHAPE create(SHAPE shape);
    public SHAPE get(String shapeId);
    public SHAPE update(String shapeId, SHAPE shape);
    public SHAPE delete(String shapeId);
}
