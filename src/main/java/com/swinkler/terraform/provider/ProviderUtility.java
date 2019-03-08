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
import java.util.List;
import com.google.common.collect.Lists;

public class ProviderUtility {

    public static void scheduleSetBlock(Block block, Material material, int ticks){
        ProviderUtility.scheduleSetBlock(block,material,ticks,null);
    }

    public static void scheduleSetBlocks(List<Block> blocks, Material material){
        ProviderUtility.scheduleSetBlocks(blocks,material, null);
    }

    private static int createSeconds=5;
    private static int ticksPerSecond=20;
    private static int skipTicks=4;
    private static double createTicks=(createSeconds*ticksPerSecond)/skipTicks;
    public static void scheduleSetBlocks(List<Block> blocks, Material material,  Particle particle){
	int batchSize = (int)(blocks.size()/createTicks);
        int i = 0;
        for (List<Block> batchBlocks : Lists.partition(blocks, batchSize)) {
            scheduleSetBlocks(batchBlocks, material, i, particle);
            i+=skipTicks;
        }
    }
	
    public static void scheduleSetBlocks(List<Block> blocks, Material material, int ticks,  Particle particle){
        Runnable task = () -> {
            for (int i = 0; i < blocks.size(); i++) {
                Block block = blocks.get(i);
                World world = block.getWorld();
                Location location = block.getLocation();
                BlockState blockState = block.getState();
                blockState.setType(material);
                if (particle != null){
                    world.spawnParticle(particle,location,1);
                }
                blockState.update(true);
            }
        };

        Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitService.getInstance(),task,ticks);
    }


    public static void scheduleSetBlock(Block block, Material material, int ticks,Particle particle){
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
        //return wrapFuture(block,ticks);
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
            Thread.sleep(ticks);
            return t;
        });
    }
}
