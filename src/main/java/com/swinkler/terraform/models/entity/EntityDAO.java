package com.swinkler.terraform.models.entity;
import com.swinkler.terraform.services.BukkitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.List;

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
        logger = BukkitService.getLogger();
        dirPath = BukkitService.getDataDir()+"/data/entity/";
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
            String jsonString = mapper.writeValueAsString(entity);
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
            mapper.writeValue(file,entity);
        } catch(IOException e){
            logger.severe(e.getMessage());
        }
    }

    public Entity getEntity(String entityId){
        Entity entity = null;
        try{
            String s = FileUtils.readFileToString(new File(dirPath+entityId+".json"),"UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(s,Entity.class);
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
        return entity;
    }

    public List<Entity> listEntities(int limit){
        ArrayList<Entity> entities = new ArrayList<>();
        String[] extensions = {"json"};
        ArrayList<File> files = new ArrayList<>(FileUtils.listFiles(new File(dirPath),extensions,false));
        int end = Math.min(files.size(),limit);
        for(int i =0;i<end;i++){
            String entityId = files.get(i).getName().replace(".json","");
            entities.add(getEntity(entityId));
        }

        return entities;
    }
}
