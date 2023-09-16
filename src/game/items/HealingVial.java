package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ChangeAttributeAction;
import game.actions.HealAction;

/**
 * An item that can be used to heal the player.
 */
public class HealingVial extends Item {
    private ActionList actions = new ActionList();
    /***
     * Constructor.
     */
    public HealingVial() {
        super("Healing Vial", 'a', true);
    }

    /**
     * Generates an allowable action for the owner of the Healing Vial, which is to use it and increase their health.
     *
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor owner) {
        int maxHealth = owner.getAttributeMaximum(BaseActorAttributes.HEALTH);
        float increasePercentage = 0.1f;

        // Create a ChangeAttributeAction to increase the actor's health
        ChangeAttributeAction changeAttributeAction = new ChangeAttributeAction(this, BaseActorAttributes.HEALTH,
                ActorAttributeOperations.INCREASE, (int) (maxHealth * increasePercentage), true);

        return new ActionList(changeAttributeAction);
    }
}
