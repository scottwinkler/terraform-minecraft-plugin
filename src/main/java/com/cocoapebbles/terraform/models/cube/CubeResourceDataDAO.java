package com.cocoapebbles.terraform.models.cube;
/*import com.cocoapebbles.terraform.controllers.BukkitController;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
public class CubeResourceDataDAO {
    private JavaPlugin p;
    private Gson gson;
    private String dirPath;
    private Logger logger;

    public CubeResourceDataDAO(){
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

    public CubeResourceDataDAO(JavaPlugin p){
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

    public void deleteCube(Cube cube){
        File file = new File(dirPath+"/"+cube.getId()+".json");
        try {
            FileUtils.deleteQuietly(file);
        }catch(Exception e){
            logger.severe(e.getMessage());
        }
    }

    public void updateCube(Cube cube){
        deleteCube(cube);
        createCube(cube);
    }

    public void createCube(Cube cube){
        try{
            File file = new File(dirPath+"/"+ cube.getId()+".json");
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
*/