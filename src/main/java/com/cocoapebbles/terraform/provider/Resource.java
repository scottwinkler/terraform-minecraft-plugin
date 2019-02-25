package com.cocoapebbles.terraform.provider;

import com.cocoapebbles.terraform.models.Model;
import com.cocoapebbles.terraform.models.ResourceData;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface Resource {
    Model create(ResourceData resourceData);
    Model read(String id);
    Model update(Model model);
    void delete(String id);
}
