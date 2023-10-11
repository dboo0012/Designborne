package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;

public class Destination {

    private GameMap destinationMap;
    private int x;

    private int y;

    private String mapName;

    public Destination(GameMap destinationMap, String mapName){
        this.destinationMap = destinationMap;
        this.mapName = mapName;
        x = -1;
        y = -1;
    }

    public Destination(GameMap destinationMap, String mapName, int x, int y){
        this.destinationMap = destinationMap;
        this.mapName = mapName;
        this.x = x;
        this.y = y;
    }

    public GameMap getDestinationMap() {
        return destinationMap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getMapName() {
        return mapName;
    }
}
