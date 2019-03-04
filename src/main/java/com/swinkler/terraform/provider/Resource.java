package com.swinkler.terraform.provider;

// T is the request object (e.g. ShapeRequest) and S is the model (e.g. Shape)
public interface Resource <T,S> {
    S create(T request);
    S read(String id);
    S update(String id, T request);
    void delete(String id);
}
