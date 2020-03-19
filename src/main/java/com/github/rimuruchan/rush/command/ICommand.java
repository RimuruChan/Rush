package com.github.rimuruchan.rush.command;

import com.github.rimuruchan.rush.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public abstract class ICommand extends Command {

    private final Class<?> SUB_CLASS;

    private String onlyConsole, onlyPlayer, noSubCommand, noPermission;

    public ICommand(Class<?> clz, String name, String... aliases) {
        super(name, "this command was inject by Rush", "/<command>", Arrays.asList(aliases));
        SUB_CLASS = clz;
        onlyConsole = "&c此指令只能由控制台调用!";
        onlyPlayer = "&c此指令只能由玩家调用!";
        noSubCommand = "&c未找到此子命令";
        noPermission = "&c你没有权限执行此指令!";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length >= 1) {
            Method method = getMethodByCommand(args[0]);
            if (method != null) {
                if (check(sender, method.getAnnotation(Cmd.class))) {
                    try {
                        method.invoke(this, sender, label, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noSubCommand));
            }
        } else {
            sendHelpMsg(sender);
        }
        return true;
    }

    /**
     * 检查权限、玩家、控制台
     *
     * @param sender
     * @param cmd
     * @return
     */
    private boolean check(CommandSender sender, Cmd cmd) {
        if (!sender.hasPermission(cmd.permission())) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermission));
            return false;
        } else if (cmd.cmdSender() == Cmd.CmdSender.PLAYER && !(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', onlyPlayer));
            return false;
        } else if (cmd.cmdSender() == Cmd.CmdSender.CONSOLE && sender instanceof Player) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', onlyConsole));
            return false;
        }
        return true;
    }

    /**
     * 获取处理子命令的method
     *
     * @param subCmd 请求的子命令
     * @return Method 处理请求命令的方法
     */
    private Method getMethodByCommand(String subCmd) {
        Method[] methods = SUB_CLASS.getMethods();
        for (Method method : methods) {
            Cmd cmd = method.getAnnotation(Cmd.class);
            if (cmd != null)
                if (cmd.IgnoreCase() ? subCmd.equals(cmd.value()) : subCmd.equalsIgnoreCase(cmd.value())) {
                    Parameter[] parameter = method.getParameters();
                    if (parameter.length == 3 && parameter[0].getType() == CommandSender.class && parameter[1].getType() == String.class && parameter[2].getType() == String[].class)
                        return method;
                    else
                        Rush.getInstance().getLogger().warning("found a Illegal sub command method in command " + getName() + " called: " + method.getName() + " in class: " + SUB_CLASS);
                }
        }
        return null;
    }

    /**
     * 发送帮助信息
     *
     * @return void   无返回值
     * @Param sender 被发送的对象
     */
    public abstract void sendHelpMsg(CommandSender sender);
}
