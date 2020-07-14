package com.acrylic.mounts.Mount;

import org.bukkit.entity.EntityType;

public enum MountType {

    STANDARD(0.2f, EntityType.HORSE,"Standard"),
    SKELETON(0.3f,EntityType.SKELETON_HORSE,"Skeleton"),
    ZOMBIE(0.3f,EntityType.ZOMBIE_HORSE,"Zombie");

    private final float speed;
    private final EntityType entityType;
    private final String perm;

    MountType(float speed, EntityType entityType, String perm) {
        this.speed = speed;
        this.entityType = entityType;
        this.perm = perm;
    }

    public float getSpeed() {
        return speed;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public String getPerm() {
        return "mount.use." + perm;
    }
}
