package game.actors;

import edu.monash.fit2099.engine.positions.Location;

/**
 * The Respawnable interface defines the contract for objects that can be respawned in a game.
 * Any class that implements this interface should provide a way to respawn an actor at a specific location.
 *
 * @author Meekal
 */
public interface Respawnable {

    /**
     * Respawn the actor at the specified location after it has died.
     *
     * @param deathLocation The location where the actor has died and needs to be respawned.
     */
    void respawnActor(Location deathLocation);
}
