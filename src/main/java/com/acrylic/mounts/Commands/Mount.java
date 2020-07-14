package com.acrylic.mounts.Commands;

import com.acrylic.mounts.ConfigLoader;
import com.acrylic.mounts.Mount.MountType;
import com.acrylic.version_latest.Messages.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class Mount implements CommandExecutor {

    private static final MountType[] mounts = MountType.values();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String arg1;
        if (args.length >= 1) {
            arg1 = args[0].toUpperCase();
            switch (arg1) {
                case "RELOAD":
                    if (sender.hasPermission(ConfigLoader.PERMISSION + "reload")) {
                        new ConfigLoader();
                        sender.sendMessage(ChatUtils.get(ConfigLoader.PREFIX + "Configuration Reloaded!"));
                    }
                    break;
                case "LIST":
                case "MOUNTS":
                    sender.sendMessage(ChatUtils.get(ConfigLoader.PREFIX) + "You own the following mounts:");
                    boolean hasMount = false;
                    for (MountType type : mounts) {
                        if (canUseMount(sender,type)) {
                            sender.sendMessage(ChatUtils.get(ConfigLoader.NON_PREFIX_TEXT + "&e" + type));
                            hasMount = true;
                        }
                    }
                    if (!hasMount) {
                        sender.sendMessage(ChatUtils.get(ConfigLoader.NON_PREFIX_TEXT + "&c&lNONE!"));
                    }
                    break;
                case "E":
                case "RIDE":
                case "EQUIP":
                    if (args.length >= 2) {
                        if (sender instanceof Player) {
                            try {
                                MountType type = MountType.valueOf(args[1].toUpperCase());
                                if (canUseMount(sender, type)) {
                                    com.acrylic.mounts.Mount.Mount mount = new com.acrylic.mounts.Mount.Mount((Player) sender,type);
                                } else {
                                    sender.sendMessage(ChatUtils.get(ConfigLoader.NO_PERM + "You do not own this mount!"));
                                }
                            } catch (IllegalArgumentException ex) {
                                sender.sendMessage(ChatUtils.get(ConfigLoader.NO_PERM + "This mount does not exist!"));
                            }
                        } else {
                            sender.sendMessage(ChatUtils.get(ConfigLoader.NO_PERM + "Only players can use this command."));
                        }
                    } else {
                        sendHelp(sender);
                    }
                    break;
                default:
                    sendHelp(sender);
            }
        } else {
            sendHelp(sender);
        }


        return true;
    }

    private boolean canUseMount(CommandSender p, MountType mountType) {
        return p.hasPermission(ConfigLoader.PERMISSION + ".use." + mountType.getPerm());
    }

    private void sendHelp(CommandSender p) {
        p.sendMessage(ChatUtils.get(ConfigLoader.PREFIX) + "/mounts <args>");
        if (p.hasPermission(ConfigLoader.PERMISSION + ".admin")) {
            p.sendMessage(ChatUtils.get(ConfigLoader.NON_PREFIX_TEXT + "reload &c&lADMIN COMMAND"));
            p.sendMessage(ChatUtils.get(ConfigLoader.NON_PREFIX_TEXT + "&7Reloads the plugin."));
        }
        p.sendMessage(ChatUtils.get(ConfigLoader.NON_PREFIX_TEXT + "mounts"));
        p.sendMessage(ChatUtils.get(ConfigLoader.NON_PREFIX_TEXT + "&7Views all your mounts."));
        p.sendMessage(ChatUtils.get(ConfigLoader.NON_PREFIX_TEXT + "equip <mount>"));
        p.sendMessage(ChatUtils.get(ConfigLoader.NON_PREFIX_TEXT + "&7Equips the specified mount."));
    }

}
