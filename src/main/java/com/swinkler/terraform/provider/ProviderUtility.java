package com.swinkler.terraform.provider;

import com.swinkler.terraform.models.entity.Entity;
import com.swinkler.terraform.services.BukkitService;
import com.swinkler.terraform.models.ResourceStatus;
import com.swinkler.terraform.models.DAOFactory;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

import org.bukkit.entity.EntityType;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProviderUtility {

    public static Future<Block> scheduleSetBlock(Block block, Material material, int ticks){
        return ProviderUtility.scheduleSetBlock(block,material,ticks,null);
    }

    public static Future<Block> scheduleSetBlock(Block block, Material material, int ticks,Particle particle){
        Location location = block.getLocation();
        World world = block.getWorld();
        Runnable task = () -> {
            BlockState blockState = block.getState();
            blockState.setType(material);
            if(particle!=null){
                world.spawnParticle(particle,location,1);
            }
            blockState.update(true);
        };

        Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitService.getInstance(),task,ticks);
        return wrapFuture(block,ticks);
    }

    public static void scheduleTask(Runnable runnable,int ticks){
        Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitService.getInstance(),runnable,ticks);
    }

    public static Future<org.bukkit.entity.Entity> scheduleEntity(Entity entity, int ticks) {
        Callable<org.bukkit.entity.Entity> callable = () -> {
            Location location = entity.getBukkitLocation();
            EntityType entityType = entity.getBukkitEntityType();

            //summon entity
            World world = location.getWorld();
            org.bukkit.entity.Entity newEntity = world.spawnEntity(location, entityType);
            newEntity.setCustomName(entity.getCustomName());

            //update data store
            entity.setStatus(ResourceStatus.Ready);
            DAOFactory.getEntityDAO().updateEntity(entity);
            return newEntity;
        };
        return Bukkit.getScheduler().callSyncMethod(BukkitService.getInstance(),callable);
    }

    public static <T> Future<T> wrapFuture(T t,int ticks){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(()->{
            Thread.sleep(ticks*50);
            return t;
        });
    }
}
