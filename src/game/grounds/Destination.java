package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * The Destination class represents a destination in a game map, typically used for respawning or teleporting purposes.
 */
public class Destination {

    private GameMap destinationMap;
    private int x;
    private int y;
    private String mapName;

    /**
     * Constructs a Destination object without specific coordinates.
     *
     * @param destinationMap The destination game map.
     * @param mapName The name of the destination map.
     */
    public Destination(GameMap destinationMap, String mapName) {
        this.destinationMap = destinationMap;
        this.mapName = mapName;
        x = -1;
        y = -1;
    }

    /**
     * Constructs a Destination object with specific coordinates.
     *
     * @param destinationMap The destination game map.
     * @param mapName The name of the destination map.
     * @param x The x-coordinate of the destination.
     * @param y The y-coordinate of the destination.
     */
    public Destination(GameMap destinationMap, String mapName, int x, int y) {
        this.destinationMap = destinationMap;
        this.mapName = mapName;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the destination game map.
     *
     * @return The destination game map.
     */
    public GameMap getDestinationMap() {
        return destinationMap;
    }

    /**
     * Get the x-coordinate of the destination.
     *
     * @return The x-coordinate of the destination, or -1 if not specified.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coordinate of the destination.
     *
     * @return The y-coordinate of the destination, or -1 if not specified.
     */
    public int getY() {
        return y;
    }

    /**
     * Get the name of the destination map.
     *
     * @return The name of the destination map.
     */
    public String getMapName() {
        return mapName;
    }
}

