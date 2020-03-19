package com.github.rimuruchan.rush.game;

public class Game {

    public Data blue, red;
    public GameMap map;

    public Game(Data blue, Data red, GameMap map) {
        this.blue = blue;
        this.red = red;
        this.map = map;
    }

    public void prepareStart(Data data) {
        data.player.teleport(map.blueSpawn);
        data.giveItems();
        data.game = this;

    }

    public void start() {
        prepareStart(blue);
        prepareStart(red);

    }
    
    public void stop() {
        map.clearBlock();
    }

}