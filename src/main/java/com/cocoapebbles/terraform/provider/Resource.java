package com.cocoapebbles.terraform.provider;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface Resource {
    static <T> T create(){throw new NotImplementedException();};
    static <T> T read(){throw new NotImplementedException();};
    static <T> T update(){throw new NotImplementedException();};
    static void delete(){throw new NotImplementedException();};
}
