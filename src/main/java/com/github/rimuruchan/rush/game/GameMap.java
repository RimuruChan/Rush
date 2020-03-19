package com.github.rimuruchan.rush.game;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class GameMap {

    public Set<Block> placed;
    public Location center, blueSpawn, redSpawn, pos1, pos2;
    public boolean inUse;

    public void clearBlock() {
        for (Block block : placed) {
            block.setType(Material.AIR);
        }
        placed.clear();
    }
    
}
