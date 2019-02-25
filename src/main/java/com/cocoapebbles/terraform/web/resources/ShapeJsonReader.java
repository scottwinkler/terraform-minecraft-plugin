package com.cocoapebbles.terraform.web.resources;

import com.cocoapebbles.terraform.models.Shape;
import com.cocoapebbles.terraform.models.Cube;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.InputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.*;
import java.lang.reflect.*;
import java.lang.annotation.*;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ShapeJsonReader implements MessageBodyReader<Shape> {
  private static Gson gson = new Gson();
    private static final RuntimeTypeAdapterFactory<Shape> adapter =
            RuntimeTypeAdapterFactory
                    .of(Shape.class, "type")
                    .registerSubtype(Cube.class, "cube");

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Shape.class, adapter);
        gson = gsonBuilder.create();
    }
	
  @Override
  public boolean isReadable(Class<?> type, Type genericType,
      Annotation[] annotations,MediaType mediaType) {
    return true;
  }

  @Override
  public Shape readFrom(Class<Shape> type, Type genericType, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
      InputStream entityStream) throws IOException, WebApplicationException {
    System.out.println("readFrom");

    StringWriter writer = new StringWriter();
    IOUtils.copy(entityStream, writer, "UTF-8");
    String json = writer.toString();
    System.out.println(json);

    return gson.fromJson(json, Shape.class);
  }
}
