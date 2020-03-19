package com.github.rimuruchan.rush.game;

import java.util.HashMap;
import java.util.Map;

import com.github.rimuruchan.rush.util.ItemUtil;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Data {

    public static Map<Player, Data> data = new HashMap<>();

    public Player player;
    public Game game;
    public int point = 0;

    public Data(Player player) {
        this.player = player;
    }

    public void giveItems() {
        player.getInventory().addItem(ItemUtil.KNOCKBACK_STICK, new ItemStack(Material.SANDSTONE, 16),
                new ItemStack(Material.DIAMOND_PICKAXE));
        
    }

}