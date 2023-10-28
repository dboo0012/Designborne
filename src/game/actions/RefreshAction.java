package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.RefreshingFlask;

/**
 * An action to recover Player's stamina.
 *
 * @author Daryl
 */
public class RefreshAction extends Action {
    private final RefreshingFlask refreshingFlask;

    /**
     * Constructor.
     * @param refreshingFlask the refreshing flask instance.
     */

    public RefreshAction(RefreshingFlask refreshingFlask) {
        this.refreshingFlask = refreshingFlask;
    }

    /**
     * Recovers stamina if player is not at full stamina.
     * Item is removed after player is healed.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action after it is processed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getAttribute(BaseActorAttributes.STAMINA) < actor.getAttributeMaximum(BaseActorAttributes.STAMINA)) {
            int gainedStamina = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.2);
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, gainedStamina);
            actor.removeItemFromInventory(refreshingFlask);
            return actor + String.format(" has gained %d stamina points.", gainedStamina);
        }
        return actor + " is full of energy.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + String.format(" gains stamina using %s." ,refreshingFlask.toString());
    }
}
