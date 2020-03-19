package com.github.rimuruchan.rush;

import java.io.File;
import java.io.IOException;

import com.github.rimuruchan.rush.command.CommandManager;
import com.github.rimuruchan.rush.listener.PlayerListener;
import com.github.rimuruchan.rush.util.ConfigUtil;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Rush extends JavaPlugin {

    public Configuration config;
    public CommandManager cm;
    public Location lobby;

    @Override
    public void onEnable() {
        config = ConfigUtil.loadConfig(new File(getDataFolder(), "config.yml"));
        cm = new CommandManager();
        lobby = (Location) config.get("lobby", getServer().getWorlds().get(0).getSpawnLocation());
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        try {
            ((FileConfiguration) config).save(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Rush getInstance() {
        return (Rush) Bukkit.getPluginManager().getPlugin("Rush");
    }

    
}
