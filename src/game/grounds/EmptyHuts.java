package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.ActorSpawn;

/**
 * A class that represents an empty hut that spawns enemies.
 */
public class EmptyHuts extends Spawner{
    private ActorSpawn actorToSpawn;
    private GameMap map;

    /**
     * Constructor.
     * @param map The map where the actor will be spawned
     * @param actorToSpawn The actor to be spawned (enemy)
     */
    public EmptyHuts(GameMap map, ActorSpawn actorToSpawn) {
        super('h', map, actorToSpawn);
        this.map = map;
        this.actorToSpawn = actorToSpawn;
    }
}
