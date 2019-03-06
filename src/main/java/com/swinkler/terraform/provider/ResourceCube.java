package com.swinkler.terraform.provider;

import com.swinkler.terraform.models.ResourceStatus;
import com.swinkler.terraform.models.DAOFactory;
import com.swinkler.terraform.models.shape.CubeDimensions;
import com.swinkler.terraform.models.shape.ShapeDAO;
import com.swinkler.terraform.models.shape.ShapeRequest;
import com.swinkler.terraform.services.BukkitService;
import com.swinkler.terraform.models.shape.Shape;
import org.bukkit.*;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
;
import java.util.stream.Collectors;

public class ResourceCube implements Resource<ShapeRequest, Shape> {

    public Shape create(ShapeRequest shapeRequest) {
        // Instantiate new shape and set attributes from request data
        Shape shape = new Shape(shapeRequest);

        // Place blocks in world
        ArrayList<Block> placedBlocks = placeBlocks(shape);
        List<Material> previousData = placedBlocks.stream().map(b -> {
            return b.getBlockData().getMaterial();
        }).collect(Collectors.toList());
        shape.setBukkitPreviousData(previousData);

        // Save shape in DAO
        ShapeDAO shapeDAO = DAOFactory.getShapeDAO();
        shapeDAO.saveShape(shape);

        // Update state when the resource has finished creating
        ProviderUtility.scheduleTask(() -> {
            shape.setStatus(ResourceStatus.Ready);
            shapeDAO.updateShape(shape);
        }, placedBlocks.size());

        return shape;
    }

    public Shape read(String shapeId) {
        BukkitService.getLogger().info(shapeId);
        ShapeDAO shapeDao = ShapeDAO.getInstance();
        Shape shape = shapeDao.getShape(shapeId);
        //quit early if not found
        if (shape==null){
            return null;
        }
        CubeDimensions cubeDimensions = new CubeDimensions(shape.getDimensions());
        Location location = shape.getBukkitLocation();
        ArrayList<Block> blocks = getRegionBlocks(cubeDimensions, location);
        Material material = shape.getBukkitMaterial();
        //if the materials dont match then update the shape data to effectively taint the resource
        for (Block block: blocks){
            Material blockMaterial = block.getBlockData().getMaterial();
            if(!blockMaterial.equals(material)){
                shape.setBukkitMaterial(blockMaterial);
                shapeDao.updateShape(shape);
                break;
            }
        }
        return shape;
    }

    public Shape update(String shapeId, ShapeRequest shapeRequest) {
        ShapeDAO shapeDAO = ShapeDAO.getInstance();
        Shape oldShape = shapeDAO.getShape(shapeId);
        removeBlocks(oldShape);
        Shape shape = new Shape(shapeRequest);
        shape.setPreviousData(oldShape.getPreviousData());
        shape.setId(oldShape.getId());
        shape.setStatus(ResourceStatus.Updating);
        shapeDAO.updateShape(shape);
        ArrayList<Block> placedBlocks = placeBlocks(shape);

        //Update state when the resource has finished creating
        int ticks = 2*placedBlocks.size();
        ProviderUtility.scheduleTask(() -> {
            shape.setStatus(ResourceStatus.Ready);
            shapeDAO.updateShape(shape);
        }, ticks);
        return shape;
    }


    public void delete(String shapeId) {
        Shape shape = read(shapeId);
        removeBlocks(shape);

        //Update state when the resource has finished creating
        int ticks = shape.getPreviousData().size();
        ShapeDAO shapeDAO = DAOFactory.getShapeDAO();
        shape.setStatus(ResourceStatus.Deleting);
        shapeDAO.updateShape(shape);
        ProviderUtility.scheduleTask(() -> {
            shapeDAO.deleteShape(shape);
        }, ticks);
    }


    public ArrayList<Block> getRegionBlocks(CubeDimensions cubeDimensions, Location location) {
        int length = cubeDimensions.getLengthX();
        int width = cubeDimensions.getWidthZ();
        int height = cubeDimensions.getHeightY();
        World w = BukkitService.getWorld();
        ArrayList<Block> blocks = new ArrayList<Block>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    Location blockLocation = location.clone().add(i,k,j);
                    blocks.add(w.getBlockAt(blockLocation));
                }
            }
        }
        return blocks;
    }

    public ArrayList<Block> placeBlocks(Shape shape) {
        CubeDimensions cubeDimensions = new CubeDimensions(shape.getDimensions());
        Location location = shape.getBukkitLocation();
        ArrayList<Block> blocks = getRegionBlocks(cubeDimensions, location);
        Material material = shape.getBukkitMaterial();
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            ProviderUtility.scheduleSetBlock(block, material, i,Particle.EXPLOSION_NORMAL);
        }
        return blocks;
    }

    public void removeBlocks(Shape shape) {
        CubeDimensions cubeDimensions = new CubeDimensions(shape.getDimensions());
        Location location = shape.getBukkitLocation();
        ArrayList<Block> blocks = getRegionBlocks(cubeDimensions, location);
        ArrayList<Material> previousData = new ArrayList<Material>(shape.getBukkitPreviousData());
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            Material material = previousData.get(i);
            ProviderUtility.scheduleSetBlock(block, material, i);
        }
    }
}
