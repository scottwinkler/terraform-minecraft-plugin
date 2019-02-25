package com.cocoapebbles.terraform.serialization;

import com.cocoapebbles.terraform.models.Model;

public interface Serializable {
    <T> T deserialize();
    <T> void serialize(T t);
}
