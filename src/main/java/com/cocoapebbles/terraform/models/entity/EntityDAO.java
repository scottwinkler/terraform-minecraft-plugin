package com.cocoapebbles.terraform.models.entity;
import com.cocoapebbles.terraform.controllers.BukkitController;
import com.cocoapebbles.terraform.serialization.SerializationUtility;
import com.cocoapebbles.terraform.serialization.SerializedEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class EntityDAO {
    private static EntityDAO instance = null;
    private static String dirPath;
    private static Logger logger;

    public static EntityDAO getInstance() {
        if (instance == null) {
            instance = new EntityDAO();
        }
        return instance;
    }

    protected EntityDAO() {
        // constructor is protected so that it can't be called from the outside
        logger = BukkitController.getLogger();
        dirPath = BukkitController.getDataDir()+"/data/entity/";
        try {
            FileUtils.forceMkdir(new File(dirPath));
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
    }

    public void deleteEntity(Entity entity){
        File file = new File(dirPath+entity.getId()+".json");
        try {
            FileUtils.deleteQuietly(file);
        }catch(Exception e){
            logger.severe(e.getMessage());
        }
    }

    public void updateEntity(Entity entity){
        File file = new File(dirPath+entity.getId()+".json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            SerializedEntity serializedEntity = SerializationUtility.serializeEntity(entity);
            String jsonString = mapper.writeValueAsString(serializedEntity);
            FileWriter fileWriter = new FileWriter(file,false);
            fileWriter.write(jsonString);
            fileWriter.close();
        }catch(Exception e){
            logger.severe(e.getMessage());
        }
    }

    public void saveEntity(Entity entity){
        try{
            File file = new File(dirPath+ entity.getId()+".json");
            ObjectMapper mapper = new ObjectMapper();
            SerializedEntity serializedEntity = SerializationUtility.serializeEntity(entity);
            mapper.writeValue(file,serializedEntity);
        } catch(IOException e){
            logger.severe(e.getMessage());
        }
    }

    public Entity getEntity(String entityId){
        Entity entity = null;
        try{
            String s = FileUtils.readFileToString(new File(dirPath+entityId+".json"),"UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            //SerializedEntity serializedEntity = SerializationUtility.serializeEntity(entity);
            SerializedEntity serializedEntity = mapper.readValue(s,SerializedEntity.class);
            return SerializationUtility.deserializeEntity(serializedEntity);
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
        return entity;
    }
}
