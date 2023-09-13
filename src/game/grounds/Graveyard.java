package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.ActorSpawn;

/**
 * A class that represents a graveyard.
 */
public class Graveyard extends Ground {
    private ActorSpawn actor;
    private GameMap map;

    /**
     * Constructor.
     * @param map The map where the actor will be spawned
     * @param actor The actor to be spawned (enemy)
     */
    public Graveyard(GameMap map, ActorSpawn actor) {
        super('n');
        this.map = map;
        this.actor = actor;
    }

    /**
     * Tick method for the Graveyard.
     * Every tick, if the spawn probability allows, it will spawn an instance of the actor around the object (graveyard).
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        Actor actor = this.actor.spawn(); // Spawn an instance of the actor

        // Spawn actor around the object (graveyard) if spawnable
        if (actor != null) {
            for(Exit exit: location.getExits()){
                Location destination = exit.getDestination();
                if(!destination.containsAnActor()){
                    map.at(destination.x(), destination.y()).addActor(actor);
                    break;
                }
            }
        }
    }
}
