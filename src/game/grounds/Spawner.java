package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.ActorSpawn;
import game.actors.EnemyActor;

/**
 * An abstract class that represents a spawner.
 *
 * @author Daryl
 */
public abstract class Spawner extends Ground {
    private ActorSpawn actorToSpawn;
    private GameMap map;

    /**
     * Constructor.
     * @param displayChar The character to display
     * @param map The map where the actor will be spawned
     * @param actor The actor to be spawned (enemy)
     */
    public Spawner(char displayChar, GameMap map, ActorSpawn actor) {
        super(displayChar);
        this.map = map;
        this.actorToSpawn = actor;
    }

    /**
     * Tick method for the Graveyard.
     * Every tick, if the spawn probability allows, it will spawn an instance of the actor around the object (graveyard).
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        spawnEnemy(location, actorToSpawn);
    }

    /**
     * Spawns an instance of the actor around the object if the spawn probability allows.
     */
    public void spawnEnemy(Location location, ActorSpawn actorToSpawn) {
    	Actor actor = actorToSpawn.spawn(); // Spawn an instance of the actor

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
