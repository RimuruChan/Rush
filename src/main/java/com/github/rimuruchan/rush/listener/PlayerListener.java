package com.github.rimuruchan.rush.listener;

import com.github.rimuruchan.rush.game.Data;
import com.github.rimuruchan.rush.util.PlayerUtil;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Data.data.put(event.getPlayer(), new Data(event.getPlayer()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Data.data.remove(event.getPlayer());
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;
        if (Data.data.get(event.getPlayer()).game == null) {
            event.setCancelled(true);
        } else {
            Data p = Data.data.get(event.getPlayer());
            Location block = event.getBlock().getLocation();
            Location pos1 = p.game.map.pos1;
            Location pos2 = p.game.map.pos2;
            boolean x = block.getX() < Math.max(pos1.getX(), pos2.getX())
                    && block.getX() >= Math.min(pos1.getX(), pos2.getX());
            boolean y = block.getY() < Math.max(pos1.getY(), pos2.getY())
                    && block.getY() >= Math.min(pos1.getY(), pos2.getY());
            boolean z = block.getZ() < Math.max(pos1.getZ(), pos2.getZ())
                    && block.getZ() >= Math.min(pos1.getZ(), pos2.getZ());
            if (!(x && y && z)) {
                event.setCancelled(true);
                PlayerUtil.sendMsg(event.getPlayer(), "&c你不能在这里放置方块!");
            } else
                p.game.map.placed.add(event.getBlock());
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;
        if (Data.data.get(event.getPlayer()).game == null) {
            event.setCancelled(true);
        } else {
            Data p = Data.data.get(event.getPlayer());
            if (p.game.map.placed.contains(event.getBlock())) {
                event.setCancelled(true);
                PlayerUtil.sendMsg(event.getPlayer(), "&c你只能破坏放置的方块!");
            } else
                p.game.map.placed.add(event.getBlock());
        }
    }
}