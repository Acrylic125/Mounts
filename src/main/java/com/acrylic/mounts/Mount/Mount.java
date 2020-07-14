package com.acrylic.mounts.Mount;

import com.acrylic.mounts.ConfigLoader;
import com.acrylic.mounts.Events.MountEvent;
import com.acrylic.version_latest.Messages.ChatUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

public class Mount {

    Player p;

    public Mount(Player p, MountType mountType) {
        if (MountEvent.isRiding(p)) {
            p.sendMessage(ChatUtils.get(ConfigLoader.NO_PERM + "You are already on a mount!"));
            return;
        }
        this.p = p;
        Location location = p.getLocation();
        Entity horse = location.getWorld().spawnEntity(location, mountType.getEntityType());
        horse.addPassenger(p);
        AbstractHorse abstractHorse = (AbstractHorse) horse;

        String name = ConfigLoader.MOUNT_NAME;
        name = name.replace("%player%",p.getName());
        horse.setCustomName(ChatUtils.get(name));

        double maxHealth = ((AbstractHorse) horse).getHealth();
        if (ConfigLoader.MOUNT_HEALTH != -1) { maxHealth = ConfigLoader.MOUNT_HEALTH;}
        ((AbstractHorse) horse).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        ((AbstractHorse) horse).setHealth(maxHealth);

        abstractHorse.setOwner(p);
        abstractHorse.setJumpStrength(1);
        abstractHorse.setTamed(true);
        abstractHorse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        abstractHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(mountType.getSpeed());
    }


}
