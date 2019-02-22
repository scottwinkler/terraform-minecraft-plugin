package com.cocoapebbles.terraform.provider;

import com.cocoapebbles.terraform.controllers.BukkitController;
import com.cocoapebbles.terraform.enums.ResourceStatus;
import com.cocoapebbles.terraform.models.DAOFactory;
import com.cocoapebbles.terraform.models.entity.Entity;
import com.cocoapebbles.terraform.models.entity.EntityResourceData;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.EntityType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProviderUtility {
    public static void changeBlock(Location location, String id){
        World world = location.getWorld();
        world.spawnParticle(Particle.EXPLOSION_NORMAL, location, 1);
        Block block = world.getBlockAt(location);
        BlockState state = block.getState();
        state.getType();
        state.setType(Material.getMaterial(id));
        state.update(true);
    }

    public static String checkBlock(Location location){
        World world = location.getWorld();
        Block block = world.getBlockAt(location);
        BlockState state = block.getState();
        return state.getType().name();
    }

    public static void schedulePlacement(World w, int x, int y, int z, int ticks, String id) {
        Location location = new Location(w,x,y,z);
        Runnable task = () -> {changeBlock(location, id);};

        Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitController.getInstance(),task,ticks);
    }

    public static void schedulePlacement(Location location, int ticks, String block) {
        Runnable task = () -> {
            changeBlock(location, block);
        };
        Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitController.getInstance(), task, ticks);
    }

    public static void scheduleEntity(Entity entity, int ticks) {
        Runnable task = () -> {
            EntityResourceData entityResourceData = entity.getEntityResourceData();
            Location location = entityResourceData.getLocation();
            EntityType entityType = entityResourceData.getEntityType();

            //validate customName
            String customName = entityResourceData.getCustomName();
            if (customName==null || customName.trim().equals("")){
                customName = entity.getId();
            }

            //summon entity
            World world = location.getWorld();
            org.bukkit.entity.Entity newEntity = world.spawnEntity(location, entityType);
            newEntity.setCustomName(customName);

            //update data store
            entity.setResourceStatus(ResourceStatus.Created);
            DAOFactory.getEntityDAO().updateEntity(entity);
        };
        Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitController.getInstance(), task, ticks);
    }


}
