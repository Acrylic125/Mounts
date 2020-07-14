package com.acrylic.mounts;

import com.acrylic.version_latest.Messages.ChatUtils;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigLoader {

    public static Configuration config;

    public static String PERMISSION;
    public static String PREFIX;
    public static String NON_PREFIX_TEXT;
    public static String NO_PERM;
    public static String MOUNT_NAME;
    public static double MOUNT_HEALTH;

    public ConfigLoader() {
        System.out.println(ChatUtils.get("&a&lMounts Config loading..."));
        try {
            Mounts.plugin.saveDefaultConfig();
            config = YamlConfiguration.loadConfiguration(new File(Mounts.plugin.getDataFolder() + "/config.yml"));

            PERMISSION = config.get("Permission").toString();
            PREFIX = config.get("Prefix").toString();
            NON_PREFIX_TEXT = config.get("Non-Prefix Text").toString();
            NO_PERM = config.get("No-Permission-Prefix").toString();
            MOUNT_NAME = config.get("mount name").toString();
            MOUNT_HEALTH = Double.parseDouble(config.get("mount health").toString());

            System.out.println(ChatUtils.get("&a&lDone!"));
            System.out.println(ChatUtils.get("  &bPermission: &f" + PERMISSION));
            System.out.println(ChatUtils.get("  &bPrefix: &f" + PREFIX));
            System.out.println(ChatUtils.get("  &bNon-Prefix Text: &f" + NON_PREFIX_TEXT));
            System.out.println(ChatUtils.get("  &bNo Permission Prefix: &f" + NO_PERM));
            System.out.println(ChatUtils.get("  &bMount Name: &f" + MOUNT_NAME));
            System.out.println(ChatUtils.get("  &bMount Health: &f" + MOUNT_HEALTH));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ChatUtils.get("&a&lMounts Config &c&lFAILED!&r&a&l Something went wrong! Please delete config.yml."));
        }
    }

}
