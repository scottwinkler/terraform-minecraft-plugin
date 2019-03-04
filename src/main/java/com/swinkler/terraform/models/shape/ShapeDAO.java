package com.swinkler.terraform.models.shape;

import com.swinkler.terraform.services.BukkitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.List;

public class ShapeDAO {
    private static ShapeDAO instance = null;
    private static String dirPath;
    private static Logger logger;

    public static ShapeDAO getInstance() {
        if (instance == null) {
            instance = new ShapeDAO();
        }
        return instance;
    }

    protected ShapeDAO() {
        // constructor is protected so that it can't be called from the outside
        logger = BukkitService.getLogger();
        dirPath = BukkitService.getDataDir()+"/data/shape/";
        try {
            FileUtils.forceMkdir(new File(dirPath));
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
    }

    public void deleteShape(Shape shape){
        File file = new File(dirPath+shape.getId()+".json");
        try {
            FileUtils.deleteQuietly(file);
        }catch(Exception e){
            logger.severe(e.getMessage());
        }
    }

    public void updateShape(Shape shape){
        File file = new File(dirPath+shape.getId()+".json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(shape);
            FileWriter fileWriter = new FileWriter(file,false);
            fileWriter.write(jsonString);
            fileWriter.close();
        }catch(Exception e){
            logger.severe(e.getMessage());
        }
    }

    public void saveShape(Shape shape){
        try{
            File file = new File(dirPath+shape.getId()+".json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file,shape);
        } catch(IOException e){
            logger.severe(e.getMessage());
        }
    }

    public Shape getShape(String shapeId){
        Shape shape = null;
        try{
            String s = FileUtils.readFileToString(new File(dirPath+shapeId+".json"),"UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(s,Shape.class);
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
        return shape;
    }

    public List<Shape> listShapes(int limit){
        ArrayList<Shape> shapes = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>(FileUtils.listFiles(new File(dirPath),null,false));
        for(int i =0;i<limit;i++){
            String shapeId = files.get(i).getName().split(".")[0];
            shapes.add(getShape(shapeId));
        }

        return shapes;
    }
}
