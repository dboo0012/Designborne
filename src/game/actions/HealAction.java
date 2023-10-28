package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.HealingVial;

/**
 * An action to heal the player.
 *
 * @author Daryl
 */
public class HealAction extends Action {
    private final HealingVial healingVial;

    /**
     * Constructor.
     * @param healingVial the healing vial instance.
     */
    public HealAction(HealingVial healingVial) {
        this.healingVial = healingVial;
    }

    /**
     * Heals the player if they are not at full health.
     * Item is removed after player is healed.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action after it is processed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getAttribute(BaseActorAttributes.HEALTH) < actor.getAttributeMaximum(BaseActorAttributes.HEALTH)) {
            int healedHealth = (int)(actor.getAttributeMaximum(BaseActorAttributes.HEALTH) * 0.1);
            actor.heal(healedHealth);
            actor.removeItemFromInventory(healingVial); // BUG
            return actor + String.format(" has healed %d health points.", healedHealth);
        }
        return actor + " does not need healing.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + String.format(" heals using %s." ,healingVial.toString());
    }
}
