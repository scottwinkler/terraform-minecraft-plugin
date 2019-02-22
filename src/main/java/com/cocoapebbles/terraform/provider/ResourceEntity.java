package com.cocoapebbles.terraform.provider;

import com.cocoapebbles.terraform.controllers.BukkitController;
import com.cocoapebbles.terraform.enums.ResourceStatus;
import com.cocoapebbles.terraform.models.DAOFactory;
import com.cocoapebbles.terraform.models.entity.Entity;
import com.cocoapebbles.terraform.models.entity.EntityDAO;
import com.cocoapebbles.terraform.models.entity.EntityResourceData;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.UUID;

public class ResourceEntity implements Resource{
    public static Entity create(EntityResourceData entityResourceData) throws IllegalStateException{
        Entity entity = new Entity();
        String id = UUID.randomUUID().toString().substring(0,8);
        entity.setId(id);
        entity.setEntityResourceData(entityResourceData);
        ResourceStatus resourceStatus = ResourceStatus.Initializing;
        entity.setResourceStatus(resourceStatus);
        String customName = entityResourceData.getCustomName();
        LivingEntity livingEntity = getLivingEntityByCustomName(customName);
        //if entity already exists then quit
        if(livingEntity!=null) {
            throw new IllegalStateException("Entity with name: "+customName+" already exists!");
        }
        ProviderUtility.scheduleEntity(entity, 0);
        DAOFactory.getEntityDAO().saveEntity(entity);
        return entity;
    }

    public static Entity read(String entityId) {
        EntityDAO entityDAO = DAOFactory.getEntityDAO();
        Entity entity = entityDAO.getEntity(entityId);
        EntityResourceData entityResourceData = entity.getEntityResourceData();
        World world = BukkitController.getWorld();
        String customName = entityResourceData.getCustomName();
        LivingEntity livingEntity = getLivingEntityByCustomName(customName);
        if(livingEntity!=null){
            entityResourceData.setLocation(livingEntity.getLocation());
            entity.setEntityResourceData(entityResourceData);
            entityDAO.updateEntity(entity);
        } else{
            entityDAO.deleteEntity(entity);
        }
        return entity;
    }

    public static Entity update(Entity entity) {
        LivingEntity livingEntity = getLivingEntityByCustomName(entity.getEntityResourceData().getCustomName());
        if(livingEntity!=null){
            EntityResourceData entityResourceData = entity.getEntityResourceData();
            livingEntity.teleport(entityResourceData.getLocation());
        }
        return entity;
    }


    public static void delete(String entityId) {
        EntityDAO entityDAO = DAOFactory.getEntityDAO();
        Entity entity = entityDAO.getEntity(entityId);
        String customName = entity.getEntityResourceData().getCustomName();
        LivingEntity livingEntity = getLivingEntityByCustomName(customName);
        if(livingEntity!=null){
            livingEntity.remove();
        }
        entityDAO.deleteEntity(entity);
    }

    public static LivingEntity getLivingEntityByCustomName(String customName){
        World world = BukkitController.getWorld();
        List<LivingEntity> livingEntityList = world.getLivingEntities();
        LivingEntity entity = null;
        for (LivingEntity livingEntity: livingEntityList) {
            String entityCustomName = livingEntity.getCustomName();
            if (entityCustomName != null && entityCustomName.equals(customName)) {
                entity = livingEntity;
            }
        }
        return entity;
    }
}
