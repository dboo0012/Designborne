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
public class Graveyard extends Spawner {
    private ActorSpawn actorToSpawn;
    private GameMap map;

    /**
     * Constructor.
     * @param map The map where the actor will be spawned
     * @param actorToSpawn The actor to be spawned (enemy)
     */
    public Graveyard(GameMap map, ActorSpawn actorToSpawn) {
        super('n', map, actorToSpawn);
        this.map = map;
        this.actorToSpawn = actorToSpawn;
    }
}
