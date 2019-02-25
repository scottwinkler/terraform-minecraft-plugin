package com.cocoapebbles.terraform.serialization;

import com.cocoapebbles.terraform.models.entity.Entity;
import com.cocoapebbles.terraform.models.entity.EntityResourceData;
import org.bukkit.entity.EntityType;

import java.util.List;

public class SerializedEntityAdapter {
    public static SerializedEntity serializeEntity(Entity entity) {
        SerializedEntity serializedEntity = new SerializedEntity();
        serializedEntity.setId(entity.getId());
        serializedEntity.setResourceStatus(entity.getResourceStatus());
        serializedEntity.setSerializedEntityResourceData(SerializedEntityAdapter.serializeEntityResourceData(entity.getEntityResourceData()));
        return serializedEntity;
    }

    public static Entity deserializeEntity(SerializedEntity serializedEntity) {
        Entity entity = new Entity();
        entity.setId(serializedEntity.getId());
        entity.setEntityResourceData(SerializedEntityAdapter.deserializeEntityResourceData(serializedEntity.getSerializedEntityResourceData()));
        entity.setResourceStatus(serializedEntity.getResourceStatus());
        return entity;
    }

    public static SerializedEntityResourceData serializeEntityResourceData(EntityResourceData entityResourceData) {
        List<Integer> location = SerializationUtility.serializeLocation(entityResourceData.getLocation());
        String entityType = entityResourceData.getEntityType().name();
        String customName = entityResourceData.getCustomName();
        SerializedEntityResourceData serializedEntityResourceData = new SerializedEntityResourceData();
        serializedEntityResourceData.setLocation(location);
        serializedEntityResourceData.setCustomName(customName);
        serializedEntityResourceData.setEntityType(entityType);
        return serializedEntityResourceData;
    }

    public static EntityResourceData deserializeEntityResourceData(SerializedEntityResourceData serializedEntityResourceData) {
        EntityResourceData entityResourceData = new EntityResourceData();
        entityResourceData.setLocation(SerializationUtility.deserializeLocation(serializedEntityResourceData.getLocation()));
        entityResourceData.setCustomName(serializedEntityResourceData.getCustomName());
        entityResourceData.setEntityType(EntityType.valueOf(serializedEntityResourceData.getEntityType()));
        return entityResourceData;
    }
}
