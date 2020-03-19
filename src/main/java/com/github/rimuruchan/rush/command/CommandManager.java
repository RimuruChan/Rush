package com.github.rimuruchan.rush.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.github.rimuruchan.rush.Rush;

public class CommandManager {
    private List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        commands.add(new RushAdmin());
        for (Command cmd : commands)
            register(cmd);
    }

    public void addCommand(ICommand cmd) {
        commands.add(cmd);
        register(cmd);
    }

    public void register(Command cmd) {
        try {
            Field commandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMap.setAccessible(true);
            CommandMap map = (CommandMap) commandMap.get(Bukkit.getServer());
            map.register("rush", cmd);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            Rush.getInstance().getLogger().warning("Error while register Command: " + cmd + " because\n");
            Rush.getInstance().getLogger().warning(e.getLocalizedMessage());
        }
    }
}
