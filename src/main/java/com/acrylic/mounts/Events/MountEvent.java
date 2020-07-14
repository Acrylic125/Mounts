package com.acrylic.mounts.Events;

import com.acrylic.mounts.Mounts;
import org.bukkit.Location;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.List;

public class MountEvent implements Listener {
    @EventHandler
    public void dismount(EntityDismountEvent e) {
        if (e.getEntity() instanceof Player && isRiding((Player) e.getEntity())) {
            eject(e.getDismounted());
        }
    }

    @EventHandler
    public void disconnect(PlayerQuitEvent e) {
        if (isRiding(e.getPlayer())) {
            eject(e.getPlayer().getVehicle());
        }
    }

    @EventHandler
    public void disconnect(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && isRiding((Player) e.getEntity())) {
            eject(e.getEntity().getVehicle());
        }
    }

    public static boolean isRiding(Player p) {
        return p.isInsideVehicle() && p.getVehicle() instanceof AbstractHorse;
    }

    private void eject(Entity e) {
        e.eject();
        Location loc = e.getLocation().clone();
        List<Entity> passenger = e.getPassengers();
        e.remove();

        new BukkitRunnable() {
            @Override
            public void run() {
                passenger.forEach(entity -> entity.teleport(loc));
            }
        }.runTaskLater(Mounts.plugin,10);
    }

}
