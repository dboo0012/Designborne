package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that represents spawning an actor.
 *
 * @author Daryl
 */
public interface ActorSpawn {
    /**
     * Spawns an actor.
     * @return actor.
     */
    Actor spawn();

}
