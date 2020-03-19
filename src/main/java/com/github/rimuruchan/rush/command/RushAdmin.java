package com.github.rimuruchan.rush.command;

import com.github.rimuruchan.rush.Rush;
import com.github.rimuruchan.rush.command.Cmd.CmdSender;
import com.github.rimuruchan.rush.util.PlayerUtil;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RushAdmin extends ICommand {

    public RushAdmin() {
        super(RushAdmin.class, "rushadmin", "ra", "setup");
    }

    @Cmd(value = "setlobby", permission = "rush.admin", cmdSender = CmdSender.PLAYER)
    public void setlobby(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        Rush.getInstance().lobby = PlayerUtil.standardLocation(p.getLocation());
        Rush.getInstance().config.set("lobby", Rush.getInstance().lobby);
        PlayerUtil.sendMsg(sender, "&b设置成功");
    }

    @Cmd(value = "list", permission = "rush.admin", cmdSender = CmdSender.BOTH)
    public void list(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        Rush.getInstance().lobby = PlayerUtil.standardLocation(p.getLocation());
        Rush.getInstance().config.set("lobby", Rush.getInstance().lobby);
        PlayerUtil.sendMsg(sender, "&b设置成功");
    }

    @Cmd(value = "add", permission = "rush.admin", cmdSender = CmdSender.PLAYER)
    public void add(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        Rush.getInstance().lobby = PlayerUtil.standardLocation(p.getLocation());
        Rush.getInstance().config.set("lobby", Rush.getInstance().lobby);
        PlayerUtil.sendMsg(sender, "&b设置成功");
    }
    @Cmd(value = "remove", permission = "rush.admin", cmdSender = CmdSender.PLAYER)
    public void remove(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        Rush.getInstance().lobby = PlayerUtil.standardLocation(p.getLocation());
        Rush.getInstance().config.set("lobby", Rush.getInstance().lobby);
        PlayerUtil.sendMsg(sender, "&b设置成功");
    }



    @Override
    public void sendHelpMsg(CommandSender sender) {

    }

}