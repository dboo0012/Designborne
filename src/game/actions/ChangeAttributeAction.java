package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action that allows an actor to consume an item, resulting in a change to one of the actor's attributes.
 *
 * @author Meekal
 */
public class ChangeAttributeAction extends Action {

    private Item item; // The item to be consumed
    private BaseActorAttributes attributeAffected; // The attribute affected by the consumption
    private ActorAttributeOperations operation; // The operation applied to the attribute
    private int amountAffected; // The amount by which the attribute is modified
    private boolean singleConsume; // Indicates whether the item is consumed and removed from inventory after use

    /**
     * Constructor to create a ChangeAttributeAction.
     *
     * @param item              The item to be consumed.
     * @param attributeAffected The attribute affected by the consumption.
     * @param operation         The operation applied to the attribute.
     * @param amountAffected    The amount by which the attribute is modified.
     * @param singleConsume     Indicates whether the item is consumed and removed from inventory after use.
     */
    public ChangeAttributeAction(Item item, BaseActorAttributes attributeAffected,
                                 ActorAttributeOperations operation, int amountAffected, boolean singleConsume) {
        this.item = item;
        this.attributeAffected = attributeAffected;
        this.operation = operation;
        this.amountAffected = amountAffected;
        this.singleConsume = singleConsume;
    }


    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.modifyAttribute(attributeAffected, operation, amountAffected);

        if (singleConsume) {
            actor.removeItemFromInventory(item);
        }

        return String.format("%s consumed %s", actor, item);

    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return The action description to be displayed on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Consume %s (%s + %d)", item, attributeAffected, amountAffected);
    }
}
