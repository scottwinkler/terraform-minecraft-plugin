package com.cocoapebbles.terraform.models.cube;

import com.cocoapebbles.terraform.controllers.BukkitController;
import com.cocoapebbles.terraform.models.Cube;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class CubeDAO {
    private static CubeDAO instance = null;
    private static String dirPath;
    private static Logger logger = BukkitController.getLogger();
    private JavaPlugin p;

    public static synchronized CubeDAO getInstance() {
        if (instance == null) {
            instance = new CubeDAO();
        }
        return instance;
    }

    private Gson gson;

    public CubeDAO(){
        this.p = BukkitController.getInstance();
        dirPath = BukkitController.getDataDir();
        try {
            FileUtils.forceMkdir(new File(dirPath));
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
        gson = new Gson();
    }

    public void deleteCube(Cube cube){
        File file = new File(dirPath+"/"+cube.id+".json");
        try {
            FileUtils.deleteQuietly(file);
        }catch(Exception e){
            logger.severe(e.getMessage());
        }
    }

    public void updateCube(Cube cube){
        deleteCube(cube);
        saveCube(cube);
    }

    public void saveCube(Cube cube){
        try{
            File file = new File(dirPath+"/"+ cube.id+".json");
            FileUtils.write(file,gson.toJson(cube),"UTF-8");
        } catch(IOException e){
            logger.severe(e.getMessage());
        }
    }

    public Cube getCube(String cubeId){
        Cube cube = null;
        try{
            String s = FileUtils.readFileToString(new File(dirPath+"/"+cubeId+".json"),"UTF-8");
            cube = gson.fromJson(s,Cube.class);
        }catch(IOException e){
            logger.severe(e.getMessage());
        }
        return cube;
    }
}
