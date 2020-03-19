package com.github.rimuruchan.rush.game;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class GameMap {

    public List<Block> placed;
    public Location center;
    public Location blueSpawn;
    public Location redSpawn;
    public Location pos1;
    public Location pos2;
    public boolean inUse;

    public void clearBlock(){
        for (Block block : placed) {
            block.setType(Material.AIR);
        }
    }
}
