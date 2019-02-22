package com.cocoapebbles.terraform.models.cylinder;
/*
import com.cocoapebbles.terraform.controllers.BukkitController;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
public class CylinderResourceDataDAO {
    private JavaPlugin p;
    private Gson gson;
    private String dirPath;
    private Logger logger;

    public CylinderResourceDataDAO(){
        this.p = BukkitController.getInstance();
        this.logger = BukkitController.getLogger();
        dirPath = BukkitController.getDataDir();
        try {
            FileUtils.forceMkdir(new File(dirPath));
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
        gson = new Gson();
    }

    public CylinderResourceDataDAO(JavaPlugin p){
        this.p = p;
        this.logger = p.getLogger();
        dirPath = p.getDataFolder().getAbsolutePath();
        try {
            FileUtils.forceMkdir(new File(dirPath));
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
        gson = new Gson();
    }

    public void deleteCylinder(Cylinder cylinder){
        File file = new File(dirPath+"/"+cylinder.getId()+".json");
        try {
            FileUtils.deleteQuietly(file);
        }catch(Exception e){
            logger.severe(e.getMessage());
        }
    }

    public void updateCylinder(Cylinder cylinder){
        deleteCylinder(cylinder);
        createCylinder(cylinder);
    }

    public void createCylinder(Cylinder cylinder){
        try{
            File file = new File(dirPath+"/"+ cylinder.getId()+".json");
            FileUtils.write(file,gson.toJson(cylinder),"UTF-8");
        } catch(IOException e){
            logger.severe(e.getMessage());
        }
    }

    public Cylinder getCylinder(String cylinderId){
        Cylinder cylinder = null;
        try{
            String s = FileUtils.readFileToString(new File(dirPath+"/"+cylinderId+".json"),"UTF-8");
            cylinder = gson.fromJson(s,Cylinder.class);
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
        return cylinder;
    }
}
*/