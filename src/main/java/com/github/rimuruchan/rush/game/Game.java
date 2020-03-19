package com.github.rimuruchan.rush.game;

import org.bukkit.entity.Player;

public class Game {

    Player blue;
    Player red;
    GameMap map;

    public Game(Player blue, Player red, GameMap map) {
        this.blue = blue;
        this.red = red;
        this.map = map;
    }

    public void start() {
        
    }
    
    public void stop() {
        map.clearBlock();
    }

}