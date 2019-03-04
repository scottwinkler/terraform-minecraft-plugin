package com.swinkler.terraform.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ResourceStatus {
    @JsonProperty("initializing")
    Initializing,
    @JsonProperty("ready")
    Ready,
    @JsonProperty("deleting")
    Deleting,
    @JsonProperty("updating")
    Updating,
}
