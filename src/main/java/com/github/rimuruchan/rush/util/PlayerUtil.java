package com.github.rimuruchan.rush.util;

import com.github.rimuruchan.rush.Rush;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

public class PlayerUtil {

    public static Location standardLocation(Location loc) {
        Location newLoc = loc.clone();
        
        newLoc.setX(loc.getBlockX());
        newLoc.setY(loc.getBlockY());
        newLoc.setZ(loc.getBlockZ());
        newLoc.setPitch(0);
        float yaw = loc.getYaw();
        if (yaw > -45 || yaw < -315)
            yaw = 0;
        else if (yaw > -135 && yaw < -45)
            yaw = -90f;
        else if (yaw > -225 && yaw < -135)
            yaw = -180f;
        else if (yaw > -315 && yaw < -225)
            yaw = -270f;

        newLoc.setYaw(yaw);

        return newLoc;
    }
    
    public static void sendMsg(CommandSender sender, String... msg) {
        for (String line : msg)
            sender.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', Rush.getInstance().config.getString("prefix") + line));
    }
}