package com.swinkler.terraform.provider;

// T is the model (e.g. Player)
public interface DataResource <T> {
    T read(String id);
}
