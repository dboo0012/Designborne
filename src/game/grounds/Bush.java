package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.ActorSpawn;

/**
 * A class that represents a bush that spawns enemies.
 *
 * @author Jerry
 */
public class Bush extends Spawner{
    private ActorSpawn actorToSpawn;
    private GameMap map;

    /**
     * Constructor.
     * @param map The map where the actor will be spawned
     * @param actorToSpawn The actor to be spawned (enemy)
     */
    public Bush(GameMap map, ActorSpawn actorToSpawn) {
        super('m', map, actorToSpawn);
        this.map = map;
        this.actorToSpawn = actorToSpawn;
    }
}
