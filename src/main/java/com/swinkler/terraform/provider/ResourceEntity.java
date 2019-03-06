package com.swinkler.terraform.provider;

import com.swinkler.terraform.models.DAOFactory;
import com.swinkler.terraform.models.ResourceStatus;
import com.swinkler.terraform.models.entity.Entity;
import com.swinkler.terraform.models.entity.EntityDAO;
import com.swinkler.terraform.models.entity.EntityRequest;
import com.swinkler.terraform.services.BukkitService;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ResourceEntity implements Resource<EntityRequest, Entity> {
    public Entity create(EntityRequest entityRequest) {
        Entity entity = new Entity(entityRequest);
        org.bukkit.entity.Entity newEntity = placeEntity(entity);
        if(newEntity==null){
            return null;
        }
        entity.setStatus(ResourceStatus.Ready);
        DAOFactory.getEntityDAO().saveEntity(entity);
        return entity;
    }

    public Entity read(String entityId) {
        EntityDAO entityDAO = DAOFactory.getEntityDAO();
        Entity entity = entityDAO.getEntity(entityId);
        World world = BukkitService.getWorld();
        String customName = entity.getCustomName();
        org.bukkit.entity.Entity livingEntity = getEntity(customName,entity.getBukkitEntityType());
        if (livingEntity != null) {
            entity.setBukkitLocation(livingEntity.getLocation());
            entityDAO.updateEntity(entity);
            entity = entityDAO.getEntity(entityId);
        } else {
            entityDAO.deleteEntity(entity);
            return null;
        }
        return entity;
    }

    public Entity update(String entityId, EntityRequest entityRequest) {
        EntityDAO entityDAO = DAOFactory.getEntityDAO();
        Entity oldEntity = entityDAO.getEntity(entityId);
        Entity entity = new Entity(entityRequest);
        entity.setId(oldEntity.getId());

        // changing the entityType will force a new resource
        if(!(oldEntity.getEntityType().equals(entity.getEntityType()))){
            removeEntity(oldEntity);
            placeEntity(entity);
        }
        else{
            //get thw old entity and update it
            org.bukkit.entity.Entity bukkitEntity = getEntity(oldEntity.getCustomName(),oldEntity.getBukkitEntityType());
            if (bukkitEntity != null) {
                bukkitEntity.teleport(entity.getBukkitLocation());
                bukkitEntity.setCustomName(entity.getCustomName());
                entityDAO.updateEntity(entity);
            }
        }
        return read(entity.getId());
    }


    public void delete(String entityId) {
        EntityDAO entityDAO = DAOFactory.getEntityDAO();
        Entity entity = entityDAO.getEntity(entityId);
        removeEntity(entity);
        entityDAO.deleteEntity(entity);
    }

    public org.bukkit.entity.Entity getEntity(String customName, EntityType entityType) {
        World world = BukkitService.getWorld();
        ArrayList<org.bukkit.entity.Entity> entities = new ArrayList<org.bukkit.entity.Entity>(world.getEntitiesByClass(entityType.getEntityClass()));
        org.bukkit.entity.Entity entity = null;
        for (org.bukkit.entity.Entity ent : entities) {
            String entityCustomName = ent.getCustomName();
            if (entityCustomName != null && entityCustomName.equals(customName)) {
                entity = ent;
                break;
            }
        }
        return entity;
    }

    public org.bukkit.entity.Entity placeEntity(Entity entity) {
        String customName = entity.getCustomName();

        //validate customName
        if (customName == null || customName.trim().equals("")) {
            customName = entity.getId();
        }
        org.bukkit.entity.Entity livingEntity = getEntity(customName,entity.getBukkitEntityType());
        //if entity already exists then quit
        if (livingEntity != null) {
            BukkitService.getLogger().info( "Entity with name: " + customName + " already exists!");
            return null;
        }

        Future<org.bukkit.entity.Entity> future = ProviderUtility.scheduleEntity(entity, 0);
        org.bukkit.entity.Entity newEntity = null;
        try {
            while (!future.isDone()) {
                Thread.sleep(100);
            }
            newEntity = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return newEntity;
    }

    public void removeEntity(Entity entity) {
        String customName = entity.getCustomName();
        org.bukkit.entity.Entity livingEntity = getEntity(customName,entity.getBukkitEntityType());
        if(livingEntity!=null){
            livingEntity.remove();
        }
    }
}
