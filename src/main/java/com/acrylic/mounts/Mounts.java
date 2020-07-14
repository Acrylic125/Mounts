package com.acrylic.mounts;

import com.acrylic.mounts.Commands.Mount;
import com.acrylic.mounts.Events.MountEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Mounts extends JavaPlugin {

    public static Mounts plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        getServer().getPluginCommand("Mount").setExecutor(new Mount());
        getServer().getPluginManager().registerEvents(new MountEvent(),this);

        new ConfigLoader();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
