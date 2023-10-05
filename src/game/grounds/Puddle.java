package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ChangeAttributeAction;
import game.actions.DrinkPuddleAction;
import game.attributes.EntityTypes;

/**
 * A class that represents a puddle.
 *
 * @author Meekal
 */
public class Puddle extends Ground {
    /**
     * Constructor.
     */
    public Puddle() {
        super('~');
    }

    /**
     * Returns a list of actions that can be performed on the Puddle.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = super.allowableActions(actor, location, direction);
        if (actor.hasCapability(EntityTypes.PLAYABLE) && location.getActor() == actor){
            actions.add(new DrinkPuddleAction());
        }
        return actions;
    }
}
