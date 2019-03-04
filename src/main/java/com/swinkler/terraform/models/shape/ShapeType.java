package com.swinkler.terraform.models.shape;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ShapeType {
    @JsonProperty("cube")
    Cube,
    @JsonProperty("cylinder")
    Cylinder,
}
