package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface for objects that can be attacked by actors in the game.
 *
 * Implementing classes should provide a list of allowable actions for an actor
 * to attack the object, given the actor's position and the direction of the
 * object.
 *
 * @author Daryl
 */
public interface Attackable {
    /**
     * Returns a list of allowable actions for an actor to attack the object.
     *
     * @param otherActor The actor that might be performing an attack.
     * @param direction  A string representing the direction of the other actor.
     * @param map        The current GameMap.
     * @return A list of allowable actions for an attack on the object.
     */
    ActionList allowableActions(Actor otherActor, String direction, GameMap map);
}
