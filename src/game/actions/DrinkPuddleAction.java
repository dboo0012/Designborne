package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action for drinking from a puddle.
 *
 * @author Meekal
 */
public class DrinkPuddleAction extends Action {

    /**
     * Drinks from the Puddle
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int healthIncrease = 1;
        int maxStamina = actor.getAttribute(BaseActorAttributes.STAMINA);
        int staminaIncrease = (int) Math.round(maxStamina * 0.01);

        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, healthIncrease);
        actor.modifyAttribute(BaseActorAttributes.STAMINA,ActorAttributeOperations.INCREASE, staminaIncrease);

        return String.format("%s drank from the puddle (HP+%d) (Stamina+%d)", actor, healthIncrease, staminaIncrease);
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Drink puddle water";
    }
}
